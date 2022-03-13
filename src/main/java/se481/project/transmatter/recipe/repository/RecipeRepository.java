package se481.project.transmatter.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se481.project.transmatter.recipe.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
