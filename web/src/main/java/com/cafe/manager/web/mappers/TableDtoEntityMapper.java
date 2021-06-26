package com.cafe.manager.web.mappers;

import com.cafe.manager.repository.entities.TableEntity;
import com.cafe.manager.web.dtos.TableDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TableDtoEntityMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "tableDto.id")
    TableEntity tableDtoToEntity(TableDto tableDto);

    @Mapping(target = "id", source = "tableEntity.uuid")
    TableDto tableEntityToDto(TableEntity tableEntity);
}