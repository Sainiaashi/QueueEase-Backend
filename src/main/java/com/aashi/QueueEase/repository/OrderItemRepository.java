package com.aashi.QueueEase.repository;

import com.aashi.QueueEase.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}