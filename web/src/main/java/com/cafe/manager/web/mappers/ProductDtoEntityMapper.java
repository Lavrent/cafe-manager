package com.cafe.manager.web.mappers;

import com.cafe.manager.repository.entities.ProductEntity;
import com.cafe.manager.web.dtos.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductDtoEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", source = "productDto.id")
    ProductEntity productDtoToEntity(ProductDto productDto);

    @Mapping(target = "id", source = "productEntity.uuid")
    ProductDto productEntityToDto(ProductEntity productEntity);
}