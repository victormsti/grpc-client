package com.victormsti.grpc.client.schedules;

import com.victormsti.grpc.client.services.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotificationSchedule {

    private final NotificationService service;

    public NotificationSchedule(NotificationService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 600000) // Run on every 10 minutes
    public void uploadFileToServer() {
        service.subscribeForNotifications(UUID.randomUUID().toString());
    }
}
