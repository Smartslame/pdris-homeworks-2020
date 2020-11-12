package ru.mipt.smartslame.pdris.hw2.services;

import ru.mipt.smartslame.pdris.hw2.entity.AuditStamp;

import java.util.*;
import java.util.stream.Collectors;

public class AuditService {
    private final Map<String, List<AuditStamp>> audit;

    public AuditService() {
        audit = new HashMap<>();
    }

    public void addRecord(String userName, AuditStamp auditStamp) {
        audit.compute(userName, (name, stamps) -> {
            if (stamps == null) {
                stamps = new ArrayList<AuditStamp>();
            }

            stamps.add(auditStamp);

            return stamps;

        });
    }

    public Map<String, List<AuditStamp>> getAllRecords() {
        return audit;
    }

    public Map<String, List<AuditStamp>> getRecordsByUser(String username) {
        return audit.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(username))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, List<AuditStamp>> getRecords(String userName) {
        if (Objects.nonNull(userName)) {
            return getRecordsByUser(userName);
        } else {
            return getAllRecords();
        }
    }
}
