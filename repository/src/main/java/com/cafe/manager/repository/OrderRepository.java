package com.cafe.manager.repository;

import com.cafe.manager.repository.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findAllByTableUuid(String uuid);

    Optional<OrderEntity> findByUuid(String uuid);
}