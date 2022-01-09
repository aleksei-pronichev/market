package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.OrderDto;
import com.geekbrains.spring.web.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConverterImpl implements OrderConverter {

    private final OrderItemConverter orderItemConverter;

    @Override
    public OrderDto toDto(OrderEntity order) {
        log.trace("Convert orderEntity to dto: {}", order);
        OrderDto dto = new OrderDto();

        dto.setId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setAddress(order.getAddress());
        dto.setPhone(order.getPhone());
        dto.setOrderItems(
                order.getOrderItemEntities().stream()
                        .map(orderItemConverter::toDto)
                        .collect(Collectors.toList())
        );

        log.trace("Dto converted: {}", dto);
        return dto;
    }
}
