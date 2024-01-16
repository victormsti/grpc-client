package com.victormsti.grpc.client.observers;

import com.victormsti.UploadStatus;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class UploadFileStreamObserver implements StreamObserver<UploadStatus> {

    @Override
    public void onNext(UploadStatus status) {
        System.out.println("Server response: " + status.getMessage());
    }

    @Override
    public void onError(Throwable t) {
        System.err.println("Error: " + t.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("File upload completed");
    }
}
