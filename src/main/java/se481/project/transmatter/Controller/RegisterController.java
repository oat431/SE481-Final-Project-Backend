package se481.project.transmatter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se481.project.transmatter.security.entity.Authority;
import se481.project.transmatter.security.entity.User;
import se481.project.transmatter.security.repository.AuthorityRepository;
import se481.project.transmatter.security.repository.UserRepository;
import se481.project.transmatter.util.TransMatterMapper;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        String testName = user.getUsername();
        String testEmail = user.getEmail();
        if(userRepository.findByUsername(testName)==null && userRepository.findByEmail(testEmail)==null){
            Authority authUser = authorityRepository.getById(1L);
            String password = encoder.encode(user.getPassword());
            user.setPassword(password);
            user.setEnabled(true);
            user.getAuthorities().add(authUser);
            User u = userRepository.save(user);
            return ResponseEntity.ok(TransMatterMapper.INSTANCE.getUserDTO(u));
        }
        // should be bad request

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
