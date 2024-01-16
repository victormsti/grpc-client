package com.victormsti.grpc.client.services.impl;

import com.victormsti.FileChunk;
import com.victormsti.FileUploadServiceGrpc;
import com.victormsti.UploadStatus;
import com.victormsti.grpc.client.TestStreamObserver;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class FileUploadServiceImplTest {

    @Mock
    private FileUploadServiceGrpc.FileUploadServiceStub stub;

    @Mock
    private StreamObserver<UploadStatus> responseObserver;

    @InjectMocks
    private FileUploadServiceImpl fileUploadService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void uploadFile_shouldSendFileChunks() {
        TestStreamObserver<FileChunk> fileChunkStreamObserver = new TestStreamObserver<>();
        when(stub.uploadFile(responseObserver)).thenReturn(fileChunkStreamObserver);

        fileUploadService.uploadFile();

        verify(stub, times(1)).uploadFile(eq(responseObserver));
    }
}
