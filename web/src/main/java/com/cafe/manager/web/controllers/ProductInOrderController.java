package com.cafe.manager.web.controllers;

import com.cafe.manager.web.dtos.ProductInOrderDto;
import com.cafe.manager.web.services.ProductInOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/orders/product-in-order")
public class ProductInOrderController {
    private final ProductInOrderService productInOrderService;

    @PostMapping
    public ResponseEntity<ProductInOrderDto> createProductInOrder(@RequestBody ProductInOrderDto productInOrderDto) {
        return ResponseEntity.ok(productInOrderService.createProductInOrder(productInOrderDto));
    }

    @PutMapping
    public ResponseEntity<ProductInOrderDto> updateProductInOrder(@RequestBody ProductInOrderDto productInOrderDto) {
        return ResponseEntity.ok(productInOrderService.updateProductInOrder(productInOrderDto));
    }
}
