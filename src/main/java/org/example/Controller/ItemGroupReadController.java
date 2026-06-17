package org.example.Controller;

import org.example.ItemGroup;
import org.example.Service.ItemGroupReadService;
import org.example.grpc.readservice.*;
import org.springframework.beans.factory.annotation.Autowired;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import java.util.List;

import io.grpc.Status;

@GrpcService
public class ItemGroupReadController extends ReadServiceGrpc.ReadServiceImplBase {

    @Autowired
    private ItemGroupReadService itemGroupReadService;

    @Override
    public void getItemGroup(UserRequest request, StreamObserver<UserResponseList> responseObserver) {
        try {
            List<ItemGroup> internalData = itemGroupReadService
                    .findItemGroupByName(request.getName());
            UserResponseList.Builder responseList = UserResponseList.newBuilder();
            for (ItemGroup itemGroup : internalData) {
                UserResponse response = UserResponse.newBuilder()
                        .setName(itemGroup.getName())
                        .setColor(itemGroup.getColor())
                        .setTypeOfGroup(itemGroup.getTypeOfGroup())
                        .build();
                responseList.addItems(response);
            }

            responseObserver.onNext(responseList.build());
            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error processing item group inside getItemGroup")
                    .withCause(e)
                    .asRuntimeException()
            );
        }
    }

    @Override
    public void getAllItemGroups(EmptyRequest request, StreamObserver<UserResponseList> responseObserver) {
        try {
            List<ItemGroup> itemGroups = itemGroupReadService.findAllItemGroups();
            UserResponseList.Builder responseList = UserResponseList.newBuilder();
            for (ItemGroup itemGroup : itemGroups) {
                UserResponse response = UserResponse.newBuilder()
                        .setName(itemGroup.getName())
                        .setColor(itemGroup.getColor())
                        .setTypeOfGroup(itemGroup.getTypeOfGroup())
                        .build();
                responseList.addItems(response);
            }
            responseObserver.onNext(responseList.build());
            responseObserver.onCompleted();

            responseObserver.onCompleted();
        } catch (RuntimeException e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Internal Server Error processing all item groups inside getAllItemGroups")
                    .withCause(e)
                    .asRuntimeException());
        }
    }
}
