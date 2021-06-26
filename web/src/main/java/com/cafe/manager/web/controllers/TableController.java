package com.cafe.manager.web.controllers;

import com.cafe.manager.web.dtos.TableDto;
import com.cafe.manager.web.services.TableService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/tables")
public class TableController {
    private final TableService tableService;

    @PostMapping
    public ResponseEntity<TableDto> createTable(@RequestBody TableDto tableDto) {
        return ResponseEntity.ok(tableService.createTable(tableDto));
    }
}