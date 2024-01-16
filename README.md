# gRPC Client Implementation with Spring Boot

This project provides a sample implementation of a gRPC client using Spring Boot. 
It is designed to interact with the gRPC server implemented in the [gRPC Server Implementation with Spring Boot](https://github.com/victormsti/grpc-server) project.

## Project Structure

- `src/main/proto`: Contains Protocol Buffer (proto) files defining the service and message types.
- `src/main/java`: Contains the Java implementation of the gRPC client and related classes.
- `src/test/java`: Includes unit tests for the gRPC client.

## Implemented gRPC Services

### 1. File Upload Service
#### Client Streaming

The client implements a client-streaming service of the gRPC server. It allows clients to upload a file in chunks to the server.

### 2. Notification Service
#### Client for Server Streaming

The client implements a server-streaming service of the gRPC server. It connects to the server and receives periodic notifications.

### 3. Chat Service
#### Client for Bidirectional Streaming

The client implements a bidirectional-streaming service of the gRPC server. It enables real-time chat with the server.

## Running the Client
To run the gRPC client, you can use the provided Spring Boot Maven plugin:

```bash
./mvnw spring-boot:run
```

But first, remember to compile and generate the protobuf resources
```bash
./mvnw protobuf:compile
```
```bash
./mvnw protobuf:compile-custom
```

Also, ensure the gRPC server is running before executing the client.

## Running Tests
Execute the unit and integration tests using the following Maven command:
```bash
./mvnw test
```

Feel free to use and modify this project to suit your specific requirements.
