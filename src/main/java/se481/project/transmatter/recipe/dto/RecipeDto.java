package se481.project.transmatter.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    Long id;
    String title;
    String image;
    List<IngredientDto> ingredients;
    List<InstructionDto> instructions;
}
