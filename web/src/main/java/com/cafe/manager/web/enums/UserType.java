package com.cafe.manager.web.enums;

import lombok.Getter;

@Getter
public enum UserType {
    MANAGER("manager"),
    WAITER("waiter");

    private final String type;

    UserType(String type) {
        this.type = type;
    }
}