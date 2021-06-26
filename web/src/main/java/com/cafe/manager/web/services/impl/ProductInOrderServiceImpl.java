package com.cafe.manager.web.services.impl;

import com.cafe.manager.repository.OrderRepository;
import com.cafe.manager.repository.ProductInOrderRepository;
import com.cafe.manager.repository.ProductRepository;
import com.cafe.manager.repository.entities.OrderEntity;
import com.cafe.manager.repository.entities.ProductEntity;
import com.cafe.manager.repository.entities.ProductInOrderEntity;
import com.cafe.manager.web.dtos.ProductInOrderDto;
import com.cafe.manager.web.enums.Status;
import com.cafe.manager.web.services.ProductInOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
class ProductInOrderServiceImpl implements ProductInOrderService {
    private final ProductInOrderRepository productInOrderRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductInOrderDto createProductInOrder(ProductInOrderDto productInOrderDto) {
        String orderId = productInOrderDto.getOrderId();
        String productId = productInOrderDto.getProductId();

        String status = Status.ACTIVE.getValue();
        productInOrderRepository.findByOrderUuidAndProductUuidAndStatus(orderId, productId, status)
                .ifPresent(productInOrder -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Product in order with given product id and order id is still active");
                });

        OrderEntity orderEntity = orderRepository.findByUuid(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with given id was not found"));

        ProductEntity productEntity = productRepository.findByUuid(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with given id was not found"));


        ProductInOrderEntity productInOrderEntity = new ProductInOrderEntity();
        productInOrderEntity.setProduct(productEntity);
        productInOrderEntity.setOrder(orderEntity);
        productInOrderEntity.setAmount(productInOrderDto.getAmount());
        productInOrderEntity.setStatus(status);

        productInOrderRepository.save(productInOrderEntity);

        productInOrderDto.setStatus(status);

        return productInOrderDto;
    }

    @Override
    public ProductInOrderDto updateProductInOrder(ProductInOrderDto productInOrderDto) {
        ProductInOrderEntity productInOrderEntity = productInOrderRepository.findByOrderUuidAndProductUuid(productInOrderDto.getOrderId(), productInOrderDto.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product in order with given product id and order id was not found"));

        productInOrderEntity.setStatus(productInOrderDto.getStatus());
        productInOrderEntity.setAmount(productInOrderDto.getAmount());

        productInOrderRepository.save(productInOrderEntity);

        return productInOrderDto;
    }
}