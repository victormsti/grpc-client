package com.victormsti.grpc.client.services.impl;

import com.victormsti.ChatMessage;
import com.victormsti.ChatServiceGrpc;
import com.victormsti.grpc.client.observers.ChatStreamObserver;
import com.victormsti.grpc.client.services.ChatService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class ChatServiceImpl implements ChatService {

    private Integer chatDurationTime = 5;
    private final ChatServiceGrpc.ChatServiceStub stub;
    private final ChatStreamObserver responseObserver;

    public ChatServiceImpl(@GrpcClient("grpc-server") ChatServiceGrpc.ChatServiceStub stub,
                           ChatStreamObserver responseObserver) {
        this.stub = stub;
        this.responseObserver = responseObserver;
    }

    @Override
    public void startChat(String sender) {
        StreamObserver<ChatMessage> requestObserver = stub.startChat(responseObserver);

        try {
            // Simulate a conversation for 5 minutes
            long startTime = System.currentTimeMillis();
            Random random = new Random();
            while (System.currentTimeMillis() - startTime < TimeUnit.MINUTES.toMillis(chatDurationTime)) {
                // Generate a random message
                ChatMessage randomMessage = ChatMessage.newBuilder()
                        .setSender(sender)
                        .setContent("Random message: " + random.nextInt(100))
                        .build();

                // Send the message to the server
                requestObserver.onNext(randomMessage);

                // Sleep for a random interval before sending the next message
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            }

            // Complete the stream when the conversation is done
            requestObserver.onCompleted();
        } catch (Exception e) {
            System.out.println("Got an error: " + e.getMessage());
        }
    }

    public void setChatDurationTime(Integer chatDurationTime) {
        this.chatDurationTime = chatDurationTime;
    }
}
