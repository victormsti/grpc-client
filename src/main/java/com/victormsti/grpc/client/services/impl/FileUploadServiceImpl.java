package com.victormsti.grpc.client.services.impl;

import com.victormsti.FileChunk;
import com.victormsti.FileUploadServiceGrpc;
import com.victormsti.UploadStatus;
import com.victormsti.grpc.client.services.FileUploadService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static com.google.protobuf.ByteString.copyFrom;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private static final int NUMBER_OF_LINES = 10000;
    private static final int CHUNK_SIZE = 1024;

    private final FileUploadServiceGrpc.FileUploadServiceStub stub;
    private final StreamObserver<UploadStatus> responseObserver;

    public FileUploadServiceImpl(@GrpcClient("grpc-server") FileUploadServiceGrpc.FileUploadServiceStub stub,
                                 StreamObserver<UploadStatus> responseObserver) {
        this.stub = stub;
        this.responseObserver = responseObserver;
    }

    @Override
    public void uploadFile() {
        // Create a StreamObserver for the client stream
        StreamObserver<FileChunk> fileChunkStreamObserver = stub.uploadFile(responseObserver);
        sendFileChunks(fileChunkStreamObserver);
    }

    private void sendFileChunks(StreamObserver<FileChunk> fileChunkStreamObserver) {
        String mockData = generateMockData();
        byte[] mockDataBytes = mockData.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < mockDataBytes.length; i += CHUNK_SIZE) {
            int endIndex = Math.min(i + CHUNK_SIZE, mockDataBytes.length);
            byte[] chunkData = new byte[endIndex - i];
            System.arraycopy(mockDataBytes, i, chunkData, 0, chunkData.length);

            FileChunk fileChunk = createFileChunk(chunkData);
            fileChunkStreamObserver.onNext(fileChunk);
        }

        fileChunkStreamObserver.onCompleted();
    }

    private FileChunk createFileChunk(byte[] chunkData) {
        return FileChunk.newBuilder()
                .setChunkData(copyFrom(chunkData))
                .build();
    }

    private String generateMockData() {
        StringBuilder mockDataBuilder = new StringBuilder();

        for (int i = 1; i <= NUMBER_OF_LINES; i++) {
            mockDataBuilder.append("Line ")
                    .append(i)
                    .append(": Mock data for file upload at ")
                    .append(System.currentTimeMillis())
                    .append("\n");
        }

        return mockDataBuilder.toString();
    }
}
