package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.enums.OrderStatus;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record OrderRequest(
    String id,
    String status,
    String description,
    Instant registered,
    AdministratorRequest liable,
    Set<ProductRequest> inventory
) {

    public static Order toEntity(OrderRequest request) {
        Order order = new Order(
                UUID.fromString(request.id()),
                OrderStatus.toEnum(request.status()),
                request.description(),
                request.registered(),
                AdministratorRequest.toEntity(request.liable())
        );

        order.getInventory().addAll(
                request.inventory().stream()
                        .map(ProductRequest::toEntity)
                        .collect(Collectors.toSet())
        );

        return order;
    }

    public static OrderRequest toRequest(Order entity) {
        return new OrderRequest(
                entity.getId().toString(),
                entity.getStatus().toString(),
                entity.getDescription(),
                entity.getRegistered(),
                AdministratorRequest.toRequest(entity.getLiable()),
                entity.getInventory().stream()
                        .map(ProductRequest::toRequest)
                        .collect(Collectors.toSet())
        );
    }

    public static OrderRequest toRequest(OrderResponse response) {
        return new OrderRequest(
                response.id(),
                response.status(),
                response.description(),
                response.registered(),
                AdministratorRequest.toRequest(response.liable()),
                response.inventory().stream()
                        .map(ProductRequest::toRequest)
                        .collect(Collectors.toSet())
        );
    }

}
