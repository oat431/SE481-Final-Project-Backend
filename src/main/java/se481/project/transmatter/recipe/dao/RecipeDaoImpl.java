package se481.project.transmatter.recipe.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se481.project.transmatter.recipe.entity.Recipe;
import se481.project.transmatter.recipe.repository.RecipeRepository;

@Repository
public class RecipeDaoImpl implements RecipeDao {
    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public void addRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }
}
