package com.geekbrains.spring.web.dto;

import lombok.Data;

@Data
public class OrderCreateDto {
    private String address;
    private String phone;
}
