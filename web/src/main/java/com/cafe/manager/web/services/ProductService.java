package com.cafe.manager.web.services;


import com.cafe.manager.web.dtos.ProductDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
}