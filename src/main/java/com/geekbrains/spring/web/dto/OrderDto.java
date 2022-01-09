package com.geekbrains.spring.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Integer totalPrice;
    private String address;
    private String phone;
    private List<OrderItemDto> orderItems;
}
