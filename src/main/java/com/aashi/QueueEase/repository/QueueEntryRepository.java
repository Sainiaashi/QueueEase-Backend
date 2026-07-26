package com.aashi.QueueEase.repository;

import com.aashi.QueueEase.entity.QueueEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QueueEntryRepository extends JpaRepository<QueueEntry, Long> {
    List<QueueEntry> findByStatusOrderByJoinedAtAsc(QueueEntry.Status status);
}