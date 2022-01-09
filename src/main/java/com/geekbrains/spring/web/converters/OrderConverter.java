package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.OrderDto;
import com.geekbrains.spring.web.entities.OrderEntity;

public interface OrderConverter {
    OrderDto toDto(OrderEntity order);
}
