package com.cafe.manager.web.services.impl;

import com.cafe.manager.repository.TableRepository;
import com.cafe.manager.repository.UserRepository;
import com.cafe.manager.repository.entities.TableEntity;
import com.cafe.manager.repository.entities.UserEntity;
import com.cafe.manager.web.dtos.TableDto;
import com.cafe.manager.web.dtos.UserDto;
import com.cafe.manager.web.enums.UserType;
import com.cafe.manager.web.mappers.TableDtoEntityMapper;
import com.cafe.manager.web.mappers.UserDtoEntityMapper;
import com.cafe.manager.web.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TableRepository tableRepository;
    private final UserDtoEntityMapper userDtoEntityMapper;
    private final TableDtoEntityMapper tableDtoEntityMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userRepository.findByUsername(userDto.getUsername())
                .ifPresent(userEntity -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "User with given username already exists");
                });

        UserEntity userEntity = userDtoEntityMapper.userDtoToEntity(userDto);
        userEntity.setUuid(UUID.randomUUID().toString());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);

        return userDtoEntityMapper.userEntityToDto(userEntity);
    }

    @Override
    public void assignTablesToWaiter(String waiterId, List<String> tableIds) {
        UserEntity userEntity = userRepository.findByUuid(waiterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with given id not found"));

        Set<TableEntity> tables = tableRepository.findAllByUuidIn(tableIds);
        if (tables.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tables with given ids are not found");
        }

        tables.forEach(table -> table.setUser(userEntity));

        tableRepository.saveAll(tables);
    }

    @Override
    public Set<TableDto> getWaiterTables(String waiterId) {
        Set<TableEntity> tableEntities = tableRepository.findAllByUserUuid(waiterId);

        return tableEntities.stream()
                .map(tableDtoEntityMapper::tableEntityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public UserDto createFirstManager(UserDto userDto) {
        if (!UserType.MANAGER.getType().equalsIgnoreCase(userDto.getType())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The provided user is not manager");
        }

        if (userRepository.count() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "First manager already exists");
        }

        return createUser(userDto);
    }
}