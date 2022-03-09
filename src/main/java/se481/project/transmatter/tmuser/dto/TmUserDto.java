package se481.project.transmatter.tmuser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se481.project.transmatter.recipe.dto.RecipeDto;
import se481.project.transmatter.security.dto.UserDTO;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TmUserDto {
    UserDTO user;
    List<RecipeDto> mark;
}
