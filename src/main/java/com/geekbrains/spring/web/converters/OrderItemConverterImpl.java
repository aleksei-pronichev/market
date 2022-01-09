package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.OrderItemDto;
import com.geekbrains.spring.web.entities.OrderItemEntity;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.services.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderItemConverterImpl implements OrderItemConverter {

    private final ProductsService productsService;

    @Override
    public OrderItemDto toDto(OrderItemEntity orderItem) {
        log.trace("Convert orderItemEntity to dto: {}", orderItem);
        OrderItemDto dto = new OrderItemDto();

        dto.setProductId(orderItem.getProduct().getId());
        dto.setProductTitle(orderItem.getProduct().getTitle());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPricePerProduct(orderItem.getPricePerProduct());
        dto.setPrice(orderItem.getPrice());

        log.trace("Dto converted: {}", dto);
        return dto;
    }

    @Override
    public OrderItemEntity toEntity(OrderItemDto dto) {
        log.trace("Convert orderItemDto to entity: {}", dto);
        OrderItemEntity entity = new OrderItemEntity();

        entity.setQuantity(dto.getQuantity());
        entity.setPricePerProduct(dto.getPricePerProduct());
        entity.setPrice(dto.getPrice());
        entity.setProduct(
                productsService.findById(dto.getProductId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + dto.getProductId()))
        );

        log.trace("Entity converted: {}", entity);
        return entity;
    }
}
