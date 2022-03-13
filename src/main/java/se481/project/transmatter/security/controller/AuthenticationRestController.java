package se481.project.transmatter.security.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import se481.project.transmatter.security.JwtTokenUtil;
import se481.project.transmatter.security.entity.Authority;
import se481.project.transmatter.security.entity.JwtUser;
import se481.project.transmatter.security.entity.User;
import se481.project.transmatter.security.repository.AuthorityRepository;
import se481.project.transmatter.security.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import se481.project.transmatter.tmuser.entity.TmUser;
import se481.project.transmatter.tmuser.repository.TmUserRepository;
import se481.project.transmatter.util.TransMatterMapper;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    TmUserRepository tmUserRepository;

    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        User user = userRepository.findByUsername(authenticationRequest.getUsername());
        Map result = new HashMap();
        result.put("token", token);
        if(user!=null){
            result.put("user",TransMatterMapper.INSTANCE.getUserDTO(user));
        }
        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        String testName = user.getUsername();
        String testEmail = user.getEmail();
        if(userRepository.findByUsername(testName)==null && userRepository.findByEmail(testEmail)==null){
            Authority authUser = authorityRepository.getById(1L);
            String password = encoder.encode(user.getPassword());

            user.setPassword(password);
            user.setFirstname(user.getFirstname());
            user.setLastname(user.getLastname());
            user.setEnabled(true);
            user.getAuthorities().add(authUser);

            TmUser tmUser = TmUser.builder()
                                .user(user)
                                .mark(new ArrayList<>())
                                .build();

            tmUserRepository.save(tmUser);

            user.setAccount(tmUser);

            User u = userRepository.save(user);
            return ResponseEntity.ok(TransMatterMapper.INSTANCE.getUserDTO(u));
        }

        Map<String,String> error = new HashMap<>();
        error.put("message","username or email have been taken, please fill the form again");
        HttpHeaders responseHeader = new HttpHeaders();
        return new ResponseEntity<>(
                error,
                responseHeader,
                HttpStatus.BAD_REQUEST
        );
    }

}
