package com.aashi.QueueEase.service;

import com.aashi.QueueEase.dto.QueueJoinRequest;
import com.aashi.QueueEase.entity.QueueEntry;
import com.aashi.QueueEase.repository.QueueEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService {

    @Autowired
    private QueueEntryRepository queueEntryRepository;

    public QueueEntry joinQueue(QueueJoinRequest request) {
        QueueEntry entry = new QueueEntry();
        entry.setCustomerName(request.getCustomerName());
        entry.setCustomerEmail(request.getCustomerEmail());
        entry.setPartySize(request.getPartySize());
        entry.setStatus(QueueEntry.Status.WAITING);
        return queueEntryRepository.save(entry);
    }

    public List<QueueEntry> getWaitingQueue() {
        return queueEntryRepository.findByStatusOrderByJoinedAtAsc(QueueEntry.Status.WAITING);
    }

    public int getPosition(Long queueEntryId) {
        List<QueueEntry> waiting = getWaitingQueue();
        for (int i = 0; i < waiting.size(); i++) {
            if (waiting.get(i).getId().equals(queueEntryId)) {
                return i + 1;
            }
        }
        return -1;
    }

    public QueueEntry updateStatus(Long id, QueueEntry.Status status) {
        QueueEntry entry = queueEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Queue entry not found"));
        entry.setStatus(status);
        return queueEntryRepository.save(entry);
    }

    public QueueEntry getById(Long id) {
        return queueEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Queue entry not found"));
    }

    public int getEstimatedWaitMinutes(Long queueEntryId) {
    List<QueueEntry> waiting = getWaitingQueue();

    int totalPeopleAhead = 0;
    boolean found = false;

    for (QueueEntry entry : waiting) {
        if (entry.getId().equals(queueEntryId)) {
            found = true;
            break;
        }
        totalPeopleAhead += entry.getPartySize();
    }

    if (!found) {
        return 0;
    }

    int baseTimePerParty = 8;
    int extraTimePerPerson = 2;

    int partiesAhead = 0;
    for (QueueEntry entry : waiting) {
        if (entry.getId().equals(queueEntryId)) break;
        partiesAhead++;
    }

    return (partiesAhead * baseTimePerParty) + (totalPeopleAhead * extraTimePerPerson);
}
}
