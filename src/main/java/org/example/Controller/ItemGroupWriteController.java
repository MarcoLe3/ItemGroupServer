package org.example.Controller;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.ItemGroup;
import org.example.Service.ItemGroupWriteService;
import org.example.grpc.writeservice.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.example.grpc.writeservice.*;

@GrpcService
public class ItemGroupWriteController extends WriteServiceGrpc.WriteServiceImplBase {
    @Autowired
    private ItemGroupWriteService itemGroupWriteService;

    @Override
    public void writeNewItemGroup(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        ItemGroup itemGroupRequest = new ItemGroup(request.getName(), request.getColor(), request.getTypeOfGroup());
        int statusCode = itemGroupWriteService.writeItemGroup(itemGroupRequest);
        UserResponse response = UserResponse
                .newBuilder()
                .setStatus(statusCode)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateItemGroup(UpdateUserRequest request, StreamObserver<UserResponse> responseObserver) {
        ItemGroup updatedDetails = new ItemGroup(
                request.getName(),
                request.getColor(),
                request.getTypeOfGroup()
        );
        boolean allowToAddNewItemGroup = request.getAllowMissing();
        String queryId = request.getPublicId();
        int statusCode = itemGroupWriteService.updateItemGroup(queryId, updatedDetails, allowToAddNewItemGroup);
        UserResponse response = UserResponse
                .newBuilder()
                .setStatus(statusCode)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteItemGroup(DeleteUserRequest request, StreamObserver<UserResponse> responseObserver) {
        int statusCode = itemGroupWriteService.deleteItemGroup(request.getPublicId());
        UserResponse response = UserResponse
                .newBuilder()
                .setStatus(statusCode)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
