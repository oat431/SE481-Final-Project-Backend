package se481.project.transmatter.tmuser.entity;

import lombok.*;
import se481.project.transmatter.recipe.entity.Recipe;
import se481.project.transmatter.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TmUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
    User user;

    @ManyToMany
    List<Recipe> mark;

}
