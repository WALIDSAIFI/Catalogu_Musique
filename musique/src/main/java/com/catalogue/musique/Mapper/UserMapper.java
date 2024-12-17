package com.catalogue.musique.Mapper;

import com.catalogue.musique.DTO.UserDTO;
import com.catalogue.musique.Entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users toEntity(UserDTO userDTO);

    UserDTO toDTO(Users user);
}
