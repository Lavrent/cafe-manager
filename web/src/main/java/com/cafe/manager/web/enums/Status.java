package com.cafe.manager.web.enums;

import lombok.Getter;

@Getter
public enum Status {
    OPEN("open"),
    CANCELLED("cancelled"),
    CLOSED("closed"),
    ACTIVE("active");

    private final String value;

    Status(String value) {
        this.value = value;
    }
}