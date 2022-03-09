package se481.project.transmatter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se481.project.transmatter.recipe.entity.Recipe;
import se481.project.transmatter.recipe.repository.RecipeRepository;
import se481.project.transmatter.security.entity.Authority;
import se481.project.transmatter.security.entity.AuthorityName;
import se481.project.transmatter.security.entity.User;
import se481.project.transmatter.security.repository.AuthorityRepository;
import se481.project.transmatter.security.repository.UserRepository;
import se481.project.transmatter.tmuser.entity.TmUser;
import se481.project.transmatter.tmuser.repository.TmUserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Random;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    TmUserRepository tmUserRepository;


    User pun,oat;
    TmUser pun561,oat431;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        addAccount();
        addRecipe();
        addMark();
    }

    private void addAccount(){
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        PasswordEncoder p = new BCryptPasswordEncoder();
        pun = User.builder()
                .email("pun@gmail.com")
                .firstname("Thitisan")
                .username("kp")
                .enabled(true)
                .password(p.encode("123456789"))
                .lastname("Chailuek")
                .build();
        oat = User.builder()
                .email("oat@gmail.com")
                .firstname("Sahachan")
                .username("oat")
                .enabled(true)
                .password(p.encode("123456789"))
                .lastname("Tippimwong")
                .build();

        authorityRepository.save(authUser);
        pun.getAuthorities().add(authUser);
        oat.getAuthorities().add(authUser);

        oat431 = TmUser.builder()
                .user(oat)
                .mark(new ArrayList<>())
                .build();

        pun561 = TmUser.builder()
                .user(pun)
                .mark(new ArrayList<>())
                .build();

        tmUserRepository.save(oat431);
        tmUserRepository.save(pun561);

        oat.setAccount(oat431);
        pun.setAccount(pun561);

        userRepository.save(pun);
        userRepository.save(oat);

    }

    java.util.Random rand = new Random();
    Recipe[] recipes = new Recipe[10];
    public void addRecipe(){
        for(int i=0;i<10;i++){
            recipes[i] = Recipe.builder()
                    .title(rand.nextInt(13500) + 1 + "")
                    .markBy(new ArrayList<>())
                    .build();
            recipeRepository.save(recipes[i]);
        }
    }

    private void addMark(){
        for(Recipe i : recipes){
            i.getMarkBy().add(oat431);
            i.getMarkBy().add(pun561);
            recipeRepository.save(i);
        }
    }

}
