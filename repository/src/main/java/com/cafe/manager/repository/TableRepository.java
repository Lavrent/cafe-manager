package com.cafe.manager.repository;

import com.cafe.manager.repository.entities.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
    Set<TableEntity> findAllByUuidIn(List<String> uuids);

    Set<TableEntity> findAllByUserUuid(String userUuid);

    Optional<TableEntity> findByUuid(String uuid);
}