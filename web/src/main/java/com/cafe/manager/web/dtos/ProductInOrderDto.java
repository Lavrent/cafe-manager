package com.cafe.manager.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInOrderDto {
    private String orderId;
    private String productId;
    private Integer amount;
    private String status;
}