package com.victormsti.grpc.client.services.impl;

import com.victormsti.NotificationRequest;
import com.victormsti.NotificationResponse;
import com.victormsti.NotificationServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class NotificationServiceImplTest {

    @Mock
    NotificationServiceGrpc.NotificationServiceStub stub;

    @Mock
    StreamObserver<NotificationResponse> responseObserver;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void subscribeForNotifications_shouldSubscribeToReceiveNotifications() {
        String clientId = UUID.randomUUID().toString();
        var request = NotificationRequest.newBuilder().setClientId(clientId).build();

        doNothing().when(stub).sendNotifications(request, responseObserver);
        notificationService.subscribeForNotifications(clientId);

        verify(stub, times(1)).sendNotifications(eq(request), eq(responseObserver));
    }
}
