package com.victormsti.grpc.client.services.impl;

import com.victormsti.NotificationRequest;
import com.victormsti.NotificationResponse;
import com.victormsti.NotificationServiceGrpc;
import com.victormsti.grpc.client.services.NotificationService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationServiceGrpc.NotificationServiceStub stub;
    private final StreamObserver<NotificationResponse> responseObserver;

    public NotificationServiceImpl( @GrpcClient("grpc-server")NotificationServiceGrpc.NotificationServiceStub stub,
                                    StreamObserver<NotificationResponse> responseObserver) {
        this.stub = stub;
        this.responseObserver = responseObserver;
    }

    @Override
    public void subscribeForNotifications(String clientId) {
        var request = NotificationRequest.newBuilder().setClientId(clientId).build();

        // Using blocking stub, which internally handles server-streaming asynchronously
        stub.sendNotifications(request, responseObserver);
    }
}
