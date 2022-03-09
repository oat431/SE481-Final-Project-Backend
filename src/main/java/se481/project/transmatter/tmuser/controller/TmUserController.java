package se481.project.transmatter.tmuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se481.project.transmatter.recipe.entity.Recipe;
import se481.project.transmatter.tmuser.entity.TmUser;
import se481.project.transmatter.tmuser.service.TmUserService;
import se481.project.transmatter.util.TransMatterMapper;

@Controller
public class TmUserController {

    @Autowired
    TmUserService tmUserService;

    @PostMapping("/mark/{id}")
    public ResponseEntity<?> markRecipe(
            @PathVariable("id") Long id,
            @RequestBody Recipe recipe
    ){
        TmUser user = tmUserService.getUser(id);
        tmUserService.markRecipe(user,recipe);
        return ResponseEntity.ok("You marked this recipe");
    }

    @PostMapping("/unmark/{id}")
    public ResponseEntity<?> unMarkRecipe(
            @PathVariable("id") Long id,
            @RequestBody Recipe recipe
    ){
        TmUser user= tmUserService.getUser(id);
        tmUserService.unMarkRecipe(user,recipe);
        return ResponseEntity.ok("You unmark this recipe");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserData(
            @PathVariable("id") Long id
    ){
        return ResponseEntity.ok(TransMatterMapper.INSTANCE.getTmUserDto(tmUserService.getUser(id)));
    }

    @PostMapping("/search-mark")
    public ResponseEntity<?> searchMark(){
        return null;
    }
}
