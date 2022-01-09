package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.OrderItemDto;
import com.geekbrains.spring.web.entities.OrderItemEntity;

public interface OrderItemConverter {
    OrderItemDto toDto(OrderItemEntity orderItem);

    OrderItemEntity toEntity(OrderItemDto dto);
}
