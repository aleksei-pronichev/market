package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.OrderCreateDto;
import com.geekbrains.spring.web.entities.OrderEntity;
import com.geekbrains.spring.web.entities.User;

public interface OrderService {
    OrderEntity createNewOrder(User user, OrderCreateDto dto);
}
