package com.cafe.manager.web.services;


import com.cafe.manager.web.dtos.TableDto;

public interface TableService {
    TableDto createTable(TableDto tableDto);
}