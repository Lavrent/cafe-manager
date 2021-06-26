package com.cafe.manager.web.mappers;

import com.cafe.manager.repository.entities.ProductInOrderEntity;
import com.cafe.manager.web.dtos.ProductInOrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductInOrderDtoEntityMapper {
    ProductInOrderEntity productInOrderDtoToEntity(ProductInOrderDto productInOrderDto);

    ProductInOrderDto productInOrderEntityToDto(ProductInOrderEntity productInOrderEntity);
}