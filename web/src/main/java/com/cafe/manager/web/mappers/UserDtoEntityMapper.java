package com.cafe.manager.web.mappers;

import com.cafe.manager.repository.entities.UserEntity;
import com.cafe.manager.web.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "userDto.id")
    UserEntity userDtoToEntity(UserDto userDto);

    @Mapping(target = "id", source = "userEntity.uuid")
    UserDto userEntityToDto(UserEntity userEntity);
}