package com.cafe.manager.web.controllers;

import com.cafe.manager.web.dtos.TableDto;
import com.cafe.manager.web.dtos.UserDto;
import com.cafe.manager.web.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/first-manager")
    public ResponseEntity<UserDto> createFirstManager(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createFirstManager(userDto));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/{waiterId}/tables")
    public ResponseEntity<UserDto> assignTablesToWaiter(@PathVariable String waiterId, @RequestBody List<String> tableIds) {
        userService.assignTablesToWaiter(waiterId, tableIds);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{waiterId}/tables")
    public ResponseEntity<Set<TableDto>> getWaiterTables(@PathVariable String waiterId) {
        return ResponseEntity.ok(userService.getWaiterTables(waiterId));
    }
}