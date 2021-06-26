package com.cafe.manager.web.services.impl;

import com.cafe.manager.repository.ProductRepository;
import com.cafe.manager.repository.entities.ProductEntity;
import com.cafe.manager.web.dtos.ProductDto;
import com.cafe.manager.web.mappers.ProductDtoEntityMapper;
import com.cafe.manager.web.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDtoEntityMapper productDtoEntityMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity productEntity = productDtoEntityMapper.productDtoToEntity(productDto);
        productEntity.setUuid(UUID.randomUUID().toString());
        productRepository.save(productEntity);

        return productDtoEntityMapper.productEntityToDto(productEntity);
    }
}