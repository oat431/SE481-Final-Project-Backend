package se481.project.transmatter.recipe.entity;

import lombok.*;
import se481.project.transmatter.tmuser.entity.TmUser;

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
//    String image;
//
//    @OneToMany(mappedBy = "foodIngredient")
//    List<Ingredient> ingredients;
//
//    @OneToMany(mappedBy = "foodInstruction")
//    List<Instruction> instructions;

    @ManyToMany(mappedBy = "mark")
    List<TmUser> markBy;

}
