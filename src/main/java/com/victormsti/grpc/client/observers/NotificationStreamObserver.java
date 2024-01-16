package com.victormsti.grpc.client.observers;

import com.victormsti.NotificationResponse;
import com.victormsti.UploadStatus;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class NotificationStreamObserver implements StreamObserver<NotificationResponse> {

    @Override
    public void onNext(NotificationResponse response) {
        System.out.println("Received notification: " + response.getNotification());
    }

    @Override
    public void onError(Throwable t) {
        System.err.println("Error in notification stream: " + t.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Notification stream completed");
    }
}
