package se481.project.transmatter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se481.project.transmatter.recipe.entity.Ingredient;
import se481.project.transmatter.recipe.entity.Instruction;
import se481.project.transmatter.recipe.entity.Recipe;
import se481.project.transmatter.recipe.repository.IngredientRepository;
import se481.project.transmatter.recipe.repository.InstructionRepository;
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

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    InstructionRepository instructionRepository;

    @Autowired
    TmUserRepository tmUserRepository;


    User pun,oat;
    TmUser pun561,oat431;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        addAccount();
        addRecipe1();
        addRecipe2();
        addMark();
    }

    private void addAccount(){
        Authority authUser = Authority.builder().name(AuthorityName.ROLE_USER).build();
        PasswordEncoder p = new BCryptPasswordEncoder();
        pun = User.builder()
                .email("pun@gmail.com")
                .firstname("thitisan")
                .username("kp")
                .enabled(true)
                .password(p.encode("123456789"))
                .lastname("chailuek")
                .build();
        oat = User.builder()
                .email("oat@gmail.com")
                .firstname("sahachan")
                .username("oat")
                .enabled(true)
                .password(p.encode("123456789"))
                .lastname("tippimwong")
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

    Recipe recipe1;
    Recipe recipe2;
    public void addRecipe1(){
        recipe1 = Recipe.builder()
                .title("Miso-Butter Rosat Chicken With Acorn Squash Panzanella")
                .image("miso-butter-roast-chicken-acorn-squash-panzanella")
                .ingredients(new ArrayList<>())
                .instructions(new ArrayList<>())
                .markBy(new ArrayList<>())
                .build();

        recipe1.getIngredients().add(
                ingredientRepository.save(
                        Ingredient.builder()
                                .name("1.5 whole Chicken")
                                .foodIngredient(recipe1)
                                .build()
                )
        );

        recipe1.getIngredients().add(
                ingredientRepository.save(
                        Ingredient.builder()
                                .name("2.0 small acorn squash")
                                .foodIngredient(recipe1)
                                .build()
                )
        );

        recipe1.getIngredients().add(
                ingredientRepository.save(
                        Ingredient.builder()
                                .name("3.0 Small red Onion")
                                .foodIngredient(recipe1)
                                .build()
                )
        );

        recipe1.getInstructions().add(
                instructionRepository.save(
                        Instruction.builder()
                                .step(
                                        "Pat chicken dry with paper towels, season all over with 2 tsp\n" +
                                                " salt, and tie legs together with kitchen twine\n" +
                                                " Let sit at room temperature 1 hour"
                                )
                                .foodInstruction(recipe1)
                                .build()
                )
        );

        recipe1.getInstructions().add(
                instructionRepository.save(
                        Instruction.builder()
                                .step(
                                        "Using your fingers, mash flour and butter in a small bowl to combine"
                                )
                                .foodInstruction(recipe1)
                                .build()
                )
        );

        recipe1.getInstructions().add(
                instructionRepository.save(
                        Instruction.builder()
                                .step(
                                        "Serve chicken with gravy and squash panzanella alongside"
                                )
                                .foodInstruction(recipe1)
                                .build()
                )
        );
        recipeRepository.save(recipe1);
    }

    public void addRecipe2(){
        recipe2 = Recipe.builder()
                .title("Crock Pot Ribs")
                .image("crock-pot-ribs")
                .ingredients(new ArrayList<>())
                .instructions(new ArrayList<>())
                .markBy(new ArrayList<>())
                .build();

        recipe2.getIngredients().add(
                ingredientRepository.save(
                        Ingredient.builder()
                                .name("1.0 country style pork ribs")
                                .foodIngredient(recipe2)
                                .build()
                )
        );

        recipe2.getIngredients().add(
                ingredientRepository.save(
                        Ingredient.builder()
                                .name("1.0 teaspoon salt")
                                .foodIngredient(recipe2)
                                .build()
                )
        );

        recipe2.getIngredients().add(
                ingredientRepository.save(
                        Ingredient.builder()
                                .name("1.0 cup bbq sauce plus additional for serving")
                                .foodIngredient(recipe2)
                                .build()
                )
        );

        recipe2.getInstructions().add(
                instructionRepository.save(
                        Instruction.builder()
                                .step(
                                        "Combine sage, rosemary, and 6 Tbsp\n" +
                                                " melted butter in a large bowl; pour half of mixture over squash on baking sheet"
                                )
                                .foodInstruction(recipe2)
                                .build()
                )
        );

        recipe2.getInstructions().add(
                instructionRepository.save(
                        Instruction.builder()
                                .step(
                                        "salt to remaining herb butter in bowl; season with black pepper and toss to combine\n" +
                                                " Set aside"
                                )
                                .foodInstruction(recipe2)
                                .build()
                )
        );

        recipe2.getInstructions().add(
                instructionRepository.save(
                        Instruction.builder()
                                .step(
                                        "Place onion and vinegar in a small bowl; season with salt and toss to coat\n" +
                                                " Let sit, tossing occasionally, until ready to serve"
                                )
                                .foodInstruction(recipe2)
                                .build()
                )
        );
        recipeRepository.save(recipe2);
    }

    private void addMark(){
//        oat431.getMark().add(recipe1);
//        oat431.getMark().add(recipe1);
//        pun561.getMark().add(recipe1);
//        pun561.getMark().add(recipe2);
//
        recipe1.getMarkBy().add(oat431);
        recipe1.getMarkBy().add(pun561);
        recipe2.getMarkBy().add(oat431);
        recipe2.getMarkBy().add(pun561);

        recipeRepository.save(recipe1);
        recipeRepository.save(recipe2);

//        tmUserRepository.save(oat431);
//        tmUserRepository.save(pun561);
    }



}
