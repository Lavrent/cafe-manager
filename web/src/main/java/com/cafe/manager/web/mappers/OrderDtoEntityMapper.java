package com.cafe.manager.web.mappers;

import com.cafe.manager.repository.entities.OrderEntity;
import com.cafe.manager.web.dtos.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDtoEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "orderDto.id")
    OrderEntity orderDtoToEntity(OrderDto orderDto);

    @Mapping(target = "id", source = "orderEntity.uuid")
    OrderDto orderEntityToDto(OrderEntity orderEntity);
}