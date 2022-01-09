package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.converters.OrderConverter;
import com.geekbrains.spring.web.dto.OrderCreateDto;
import com.geekbrains.spring.web.dto.OrderDto;
import com.geekbrains.spring.web.entities.OrderEntity;
import com.geekbrains.spring.web.entities.User;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.services.OrderService;
import com.geekbrains.spring.web.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;
    private final UserService userService;

    @PostMapping(value = "/create")
    public OrderDto createNewOrder(
            Principal principal,
            @RequestBody OrderCreateDto orderCreateDto
    ) {
        log.debug("Create new order for user: {}", principal.getName());

        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + principal.getName()));
        OrderEntity orderEntity = orderService.createNewOrder(user, orderCreateDto);
        OrderDto orderDto = orderConverter.toDto(orderEntity);

        log.debug("New order for user: {} created: {}", principal.getName(), orderDto);
        return orderDto;
    }
}
