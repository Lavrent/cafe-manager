package com.cafe.manager.web.services;


import com.cafe.manager.web.dtos.TableDto;
import com.cafe.manager.web.dtos.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {

    UserDto createUser(UserDto userDto);

    void assignTablesToWaiter(String waiterId, List<String> tableIds);

    Set<TableDto> getWaiterTables(String waiterId);

    UserDto createFirstManager(UserDto userDto);
}
