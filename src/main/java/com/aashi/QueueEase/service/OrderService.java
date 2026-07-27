package com.aashi.QueueEase.service;

import com.aashi.QueueEase.dto.OrderItemRequest;
import com.aashi.QueueEase.dto.OrderRequest;
import com.aashi.QueueEase.entity.MenuItem;
import com.aashi.QueueEase.entity.Order;
import com.aashi.QueueEase.entity.OrderItem;
import com.aashi.QueueEase.repository.MenuItemRepository;
import com.aashi.QueueEase.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public Order placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setTableNumber(request.getTableNumber());
        order.setStatus(Order.Status.PLACED);

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {
            MenuItem menuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                    .orElseThrow(() -> new RuntimeException("Menu item not found: " + itemRequest.getMenuItemId()));

            BigDecimal subtotal = menuItem.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setSubtotal(subtotal);
            orderItem.setOrder(order);

            order.getItems().add(orderItem);
            total = total.add(subtotal);
        }

        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByTable(int tableNumber) {
        return orderRepository.findByTableNumber(tableNumber);
    }

    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateStatus(Long id, Order.Status status) {
        Order order = getById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public BigDecimal getTableBill(int tableNumber) {
        List<Order> orders = orderRepository.findByTableNumber(tableNumber);
        BigDecimal total = BigDecimal.ZERO;
        for (Order order : orders) {
            if (order.getStatus() != Order.Status.CANCELLED) {
                total = total.add(order.getTotalAmount());
            }
        }
        return total;
    }
}
