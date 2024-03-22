package com.mh.ga.administrative.models.factories;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.enums.OrderStatus;
import com.mh.ga.administrative.models.transfers.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class OrderFactory {

    public static Order createEntityWithId(UUID id) {
        Order order = new Order(
                id,
                OrderStatus.APPROVED,
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                AdministratorFactory.createEntityByID(UUID.fromString("1b813689-6fad-463c-b3c7-93b2f3e06f32"))
        );
        order.addProduct(ProductFactory.createEntityWithID(UUID.fromString("cf77d3b3-fa9d-42c9-95e4-756e7aeb4ef6")));

        return order;
    }

    public static Order createEntityWithoutId() {
        Order order = new Order(
                OrderStatus.APPROVED,
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                AdministratorFactory.createEntityByID(UUID.fromString("1b813689-6fad-463c-b3c7-93b2f3e06f32"))
        );
        order.addProduct(ProductFactory.createEntityWithID(UUID.fromString("cf77d3b3-fa9d-42c9-95e4-756e7aeb4ef6"))
        );

        return order;
    }

    public static OrderRequest createRequest() {
        return new OrderRequest(
                null,
                OrderStatus.APPROVED.toString(),
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                new AdministratorIdentityRequest(UUID.fromString("1b813689-6fad-463c-b3c7-93b2f3e06f32")),
                Set.of(new ProductIdentityRequest(UUID.fromString("cf77d3b3-fa9d-42c9-95e4-756e7aeb4ef6")))
        );
    }

    public static OrderResponse createResponseWithId(UUID id) {
        return new OrderResponse(
                id.toString(),
                OrderStatus.APPROVED.toString(),
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                new AdministratorIdentityResponse(UUID.fromString("1b813689-6fad-463c-b3c7-93b2f3e06f32")),
                Set.of(new ProductIdentityResponse(UUID.fromString("cf77d3b3-fa9d-42c9-95e4-756e7aeb4ef6")))
        );
    }

    public static OrderResponse createResponseWithoutId() {
        return new OrderResponse(
                UUID.randomUUID().toString(),
                OrderStatus.APPROVED.toString(),
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                new AdministratorIdentityResponse(UUID.fromString("1b813689-6fad-463c-b3c7-93b2f3e06f32")),
                Set.of(new ProductIdentityResponse(UUID.fromString("cf77d3b3-fa9d-42c9-95e4-756e7aeb4ef6")))
        );
    }

}
