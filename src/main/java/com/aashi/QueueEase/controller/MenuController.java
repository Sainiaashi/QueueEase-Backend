package com.aashi.QueueEase.controller;

import com.aashi.QueueEase.dto.MenuItemRequest;
import com.aashi.QueueEase.entity.MenuItem;
import com.aashi.QueueEase.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllItems() {
        return ResponseEntity.ok(menuService.getAllItems());
    }

    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableItems() {
        return ResponseEntity.ok(menuService.getAvailableItems());
    }

    @PostMapping
    public ResponseEntity<MenuItem> createItem(@Valid @RequestBody MenuItemRequest request) {
        return ResponseEntity.ok(menuService.createItem(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItem> updateItem(@PathVariable Long id, @Valid @RequestBody MenuItemRequest request) {
        return ResponseEntity.ok(menuService.updateItem(id, request));
    }

    @PatchMapping("/{id}/toggle-availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.toggleAvailability(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        menuService.deleteItem(id);
        return ResponseEntity.ok("Menu item deleted");
    }
}