package se481.project.transmatter.tmuser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TmUserController {
    @PostMapping("/mark/{recipeID}")
    public ResponseEntity<?> markRecipe(){
        return null;
    }

    @PostMapping("/unmark/{recipeID}")

    @GetMapping("/user/{userID}")
    public ResponseEntity<?> getUserData(){
        return null;
    }

    @PostMapping("/search-mark")
    public ResponseEntity<?> searchMark(){
        return null;
    }
}
