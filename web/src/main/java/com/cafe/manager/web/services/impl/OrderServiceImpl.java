package com.cafe.manager.web.services.impl;

import com.cafe.manager.repository.OrderRepository;
import com.cafe.manager.repository.TableRepository;
import com.cafe.manager.repository.entities.OrderEntity;
import com.cafe.manager.repository.entities.TableEntity;
import com.cafe.manager.web.dtos.OrderDto;
import com.cafe.manager.web.enums.Status;
import com.cafe.manager.web.mappers.OrderDtoEntityMapper;
import com.cafe.manager.web.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@AllArgsConstructor
@Service
class OrderServiceImpl implements OrderService {
    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;
    private final OrderDtoEntityMapper orderDtoEntityMapper;

    @Override
    public OrderDto createOrder(String tableId, OrderDto orderDto) {
        if (tableHasOpenOrder(tableId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The table with given id still has open order");
        }

        TableEntity tableEntity = tableRepository.findByUuid(tableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Table with given id is not found"));

        OrderEntity orderEntity = orderDtoEntityMapper.orderDtoToEntity(orderDto);
        orderEntity.setUuid(UUID.randomUUID().toString());
        orderEntity.setStatus(Status.OPEN.getValue());
        orderEntity.setTable(tableEntity);

        orderRepository.save(orderEntity);

        return orderDtoEntityMapper.orderEntityToDto(orderEntity);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.findByUuid(orderDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with given id was not found"));

        orderEntity.setStatus(orderDto.getStatus());
        orderEntity.setDescription(orderDto.getDescription());

        orderRepository.save(orderEntity);

        return orderDto;
    }


    private boolean tableHasOpenOrder(String tableId) {
        return orderRepository.findAllByTableUuid(tableId)
                .stream()
                .anyMatch(order -> Status.OPEN.getValue().equals(order.getStatus()));
    }
}