package com.victormsti.grpc.client.schedules;

import com.victormsti.grpc.client.services.FileUploadService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FileUploadSchedule {

    private final FileUploadService service;

    public FileUploadSchedule(FileUploadService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 60000) // Run every minute
    public void uploadFileToServer() {
        service.uploadFile();
    }
}
