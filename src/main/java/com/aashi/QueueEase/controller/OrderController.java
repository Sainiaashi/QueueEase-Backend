package com.aashi.QueueEase.controller;

import com.aashi.QueueEase.dto.OrderRequest;
import com.aashi.QueueEase.entity.Order;
import com.aashi.QueueEase.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.placeOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/table/{tableNumber}")
    public ResponseEntity<List<Order>> getOrdersByTable(@PathVariable int tableNumber) {
        return ResponseEntity.ok(orderService.getOrdersByTable(tableNumber));
    }

    @GetMapping("/table/{tableNumber}/bill")
    public ResponseEntity<Map<String, Object>> getTableBill(@PathVariable int tableNumber) {
        BigDecimal total = orderService.getTableBill(tableNumber);
        List<Order> orders = orderService.getOrdersByTable(tableNumber);
        return ResponseEntity.ok(Map.of(
                "tableNumber", tableNumber,
                "totalBill", total,
                "orders", orders
        ));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestParam Order.Status status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}
