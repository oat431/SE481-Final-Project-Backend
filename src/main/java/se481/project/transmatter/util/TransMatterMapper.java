package se481.project.transmatter.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se481.project.transmatter.security.dto.UserDTO;
import se481.project.transmatter.security.entity.User;
import se481.project.transmatter.tmuser.dto.TmUserDto;
import se481.project.transmatter.tmuser.entity.TmUser;

import java.util.stream.Collectors;


@Mapper(imports = Collectors.class)

public interface TransMatterMapper {
    TransMatterMapper INSTANCE = Mappers.getMapper(TransMatterMapper.class);

    UserDTO getUserDTO(User u);

    TmUserDto getTmUserDto(TmUser user);
}
