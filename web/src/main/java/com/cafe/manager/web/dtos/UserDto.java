package com.cafe.manager.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String id;
    private String type;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}