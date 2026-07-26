package com.aashi.QueueEase.controller;

import com.aashi.QueueEase.dto.QueueJoinRequest;
import com.aashi.QueueEase.entity.QueueEntry;
import com.aashi.QueueEase.service.QueueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/queue")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @PostMapping("/join")
    public ResponseEntity<QueueEntry> joinQueue(@Valid @RequestBody QueueJoinRequest request) {
        return ResponseEntity.ok(queueService.joinQueue(request));
    }

    @GetMapping("/waiting")
    public ResponseEntity<List<QueueEntry>> getWaitingQueue() {
        return ResponseEntity.ok(queueService.getWaitingQueue());
    }

    @GetMapping("/{id}/status")
public ResponseEntity<Map<String, Object>> getStatus(@PathVariable Long id) {
    QueueEntry entry = queueService.getById(id);
    int position = queueService.getPosition(id);
    int estimatedWait = queueService.getEstimatedWaitMinutes(id);
    return ResponseEntity.ok(Map.of(
            "id", entry.getId(),
            "status", entry.getStatus(),
            "position", position,
            "estimatedWaitMinutes", estimatedWait
    ));
}

    @PatchMapping("/{id}/status")
    public ResponseEntity<QueueEntry> updateStatus(@PathVariable Long id, @RequestParam QueueEntry.Status status) {
        return ResponseEntity.ok(queueService.updateStatus(id, status));
    }
}
