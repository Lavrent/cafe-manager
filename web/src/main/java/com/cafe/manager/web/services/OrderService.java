package com.cafe.manager.web.services;

import com.cafe.manager.web.dtos.OrderDto;

public interface OrderService {
    OrderDto createOrder(String tableId, OrderDto orderDto);

    OrderDto updateOrder(OrderDto orderDto);
}