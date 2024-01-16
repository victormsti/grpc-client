package com.victormsti.grpc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GrpcClientExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcClientExampleApplication.class, args);
	}

}
