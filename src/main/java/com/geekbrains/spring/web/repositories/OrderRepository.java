package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
