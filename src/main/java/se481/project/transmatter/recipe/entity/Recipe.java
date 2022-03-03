package se481.project.transmatter.recipe.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    String title;
    String image;

    @OneToMany(mappedBy = "foodIngredient")
    List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "foodInstruction")
    List<Instruction> instructions = new ArrayList<>();

}
