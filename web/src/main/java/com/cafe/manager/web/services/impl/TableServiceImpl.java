package com.cafe.manager.web.services.impl;

import com.cafe.manager.repository.TableRepository;
import com.cafe.manager.repository.entities.TableEntity;
import com.cafe.manager.web.dtos.TableDto;
import com.cafe.manager.web.mappers.TableDtoEntityMapper;
import com.cafe.manager.web.services.TableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;
    private final TableDtoEntityMapper tableDtoEntityMapper;

    @Override
    public TableDto createTable(TableDto tableDto) {
        TableEntity tableEntity = tableDtoEntityMapper.tableDtoToEntity(tableDto);
        tableEntity.setUuid(UUID.randomUUID().toString());
        tableRepository.save(tableEntity);

        return tableDtoEntityMapper.tableEntityToDto(tableEntity);
    }
}