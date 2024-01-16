package com.victormsti.grpc.client.observers;

import com.victormsti.ChatMessage;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class ChatStreamObserver implements StreamObserver<ChatMessage> {
    @Override
    public void onNext(ChatMessage message) {
        System.out.println("Received message from server: " +
                message.getSender() + ": " +
                message.getContent()
        );
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("Error in chat stream: " + throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Server has completed the chat stream.");
    }
}
