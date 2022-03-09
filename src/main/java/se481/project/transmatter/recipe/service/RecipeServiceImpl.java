package se481.project.transmatter.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se481.project.transmatter.recipe.dao.RecipeDao;
import se481.project.transmatter.recipe.entity.Recipe;

@Service
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    RecipeDao recipeDao;

    @Override
    public void addRecipe(Recipe recipe) {
        recipeDao.addRecipe(recipe);
    }
}
