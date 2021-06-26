package com.cafe.manager.repository;

import com.cafe.manager.repository.entities.ProductInOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrderEntity, Long> {
    Optional<ProductInOrderEntity> findByOrderUuidAndProductUuid(String orderUuid, String productUuid);

    Optional<ProductInOrderEntity> findByOrderUuidAndProductUuidAndStatus(String orderUuid, String productUuid, String status);
}