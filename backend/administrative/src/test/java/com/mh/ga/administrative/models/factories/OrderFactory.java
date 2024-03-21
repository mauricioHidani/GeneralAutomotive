package com.mh.ga.administrative.models.factories;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.enums.OrderStatus;
import com.mh.ga.administrative.models.transfers.*;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class OrderFactory {

    public static Order createEntityWithId(UUID id) {
        Order order = new Order(
                id,
                OrderStatus.APPROVED,
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                AdministratorFactory.createEntityByID(UUID.randomUUID())
        );
        order.addProduct(ProductFactory.createEntityWithID(UUID.randomUUID()));

        return order;
    }

    public static Order createEntityWithoutId() {
        Order order = new Order(
                OrderStatus.APPROVED,
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                AdministratorFactory.createEntityByID(UUID.randomUUID())
        );
        order.addProduct(ProductFactory.createEntityWithID(UUID.randomUUID()));

        return order;
    }

    public static OrderRequest createRequest() {
        return new OrderRequest(
                null,
                OrderStatus.APPROVED.toString(),
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                new AdministratorIdentityRequest(UUID.randomUUID()),
                Set.of(new ProductRequest(
                        UUID.randomUUID().toString(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ))
        );
    }

    public static OrderResponse createResponseWithId(UUID id) {
        return new OrderResponse(
                id.toString(),
                OrderStatus.APPROVED.toString(),
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                AdministratorFactory.createResponseByID(UUID.randomUUID()),
                Set.of(ProductFactory.createResponseWithID(UUID.randomUUID()))
        );
    }

    public static OrderResponse createResponseWithoutId() {
        return new OrderResponse(
                UUID.randomUUID().toString(),
                OrderStatus.APPROVED.toString(),
                "Uma ordem de recebimento de estoque",
                Instant.parse("2024-01-15T18:35:24.00Z"),
                AdministratorFactory.createResponseByID(UUID.randomUUID()),
                Set.of(ProductFactory.createResponseWithID(UUID.randomUUID()))
        );
    }

}
