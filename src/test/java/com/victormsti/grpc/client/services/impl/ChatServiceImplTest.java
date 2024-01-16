package com.victormsti.grpc.client.services.impl;

import com.victormsti.ChatMessage;
import com.victormsti.ChatServiceGrpc;
import com.victormsti.grpc.client.TestStreamObserver;
import com.victormsti.grpc.client.observers.ChatStreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class ChatServiceImplTest {

    @Mock
    ChatServiceGrpc.ChatServiceStub stub;

    @Mock
    ChatStreamObserver responseObserver;

    @InjectMocks
    ChatServiceImpl chatService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void startChat_shouldStartALiveChat() {
        TestStreamObserver<ChatMessage> requestObserver = new TestStreamObserver<>();
        when(stub.startChat(responseObserver)).thenReturn(requestObserver);

        chatService.setChatDurationTime(0);
        chatService.startChat("Test");

        verify(stub, times(1)).startChat(eq(responseObserver));
    }
}
