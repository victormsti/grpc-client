package com.victormsti.grpc.client.schedules;

import com.victormsti.grpc.client.services.ChatService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ChatSchedule {

    private final ChatService service;

    public ChatSchedule(ChatService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 300000) // Run every 5 minutes
    public void startChat() {
        service.startChat("Client test");
    }
}
