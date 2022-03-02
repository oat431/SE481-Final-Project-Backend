package se481.project.transmatter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se481.project.transmatter.security.entity.Authority;
import se481.project.transmatter.security.entity.AuthorityName;
import se481.project.transmatter.security.entity.User;
import se481.project.transmatter.security.repository.AuthorityRepository;
import se481.project.transmatter.security.repository.UserRepository;

import javax.transaction.Transactional;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        User pun,oat;
        PasswordEncoder p = new BCryptPasswordEncoder();
        pun = User.builder().email("pun@gmail.com").firstname("thitisan").username("kp")
                .enabled(true).password(p.encode("123456789")).lastname("chailuek").build();
        oat = User.builder().email("oat@gmail.com").firstname("sahachan").username("oat")
                .enabled(true).password(p.encode("123456789")).lastname("tippimwong").build();

        authorityRepository.save(authUser);
        pun.getAuthorities().add(authUser);
        oat.getAuthorities().add(authUser);
        userRepository.save(pun);
        userRepository.save(oat);
    }



}
