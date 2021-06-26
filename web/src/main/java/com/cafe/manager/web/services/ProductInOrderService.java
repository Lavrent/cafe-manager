package com.cafe.manager.web.services;


import com.cafe.manager.web.dtos.ProductInOrderDto;

public interface ProductInOrderService {
    ProductInOrderDto createProductInOrder(ProductInOrderDto productInOrderDto);

    ProductInOrderDto updateProductInOrder(ProductInOrderDto productInOrderDto);
}
