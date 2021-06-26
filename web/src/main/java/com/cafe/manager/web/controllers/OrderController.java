package com.cafe.manager.web.controllers;


import com.cafe.manager.web.dtos.OrderDto;
import com.cafe.manager.web.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/tables/{tableId}/orders")
    public ResponseEntity<OrderDto> createOrder(@PathVariable String tableId, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(tableId, orderDto));
    }

    @PutMapping("/orders")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.updateOrder(orderDto));
    }
}
