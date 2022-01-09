package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.converters.OrderItemConverter;
import com.geekbrains.spring.web.dto.Cart;
import com.geekbrains.spring.web.dto.OrderCreateDto;
import com.geekbrains.spring.web.entities.OrderEntity;
import com.geekbrains.spring.web.entities.User;
import com.geekbrains.spring.web.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;
    private final OrderItemConverter orderItemConverter;
    private final OrderRepository orderRepository;

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public OrderEntity createNewOrder(User user, OrderCreateDto orderCreateDto) {
        log.info("Try to create new order");

        Cart cart = cartService.getCurrentCart();

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setAddress(orderCreateDto.getAddress());
        order.setPhone(orderCreateDto.getPhone());
        order.setTotalPrice(cart.getTotalPrice());

        final OrderEntity savedOrder = orderRepository.save(order);

        order.setOrderItemEntities(
                cart.getItems().stream()
                        .map(orderItemConverter::toEntity)
                        .peek(orderItem -> {
                            orderItem.setUser(user);
                            orderItem.setOrder(savedOrder);
                        })
                        .collect(Collectors.toList())
        );
        OrderEntity returnedOrder = orderRepository.saveAndFlush(savedOrder);

        log.info("New order created: {}", returnedOrder);
        return returnedOrder;
    }
}
