package se481.project.transmatter.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se481.project.transmatter.recipe.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
}
