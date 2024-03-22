package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.enums.OrderStatus;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record OrderResponse(
    String id,
    String status,
    String description,
    Instant registered,
    AdministratorIdentityResponse liable,
    Set<ProductIdentityResponse> inventory
) {

    public static Order toEntity(OrderResponse response) {
        Order order = new Order(
                UUID.fromString(response.id()),
                OrderStatus.toEnum(response.status()),
                response.description(),
                response.registered(),
                AdministratorIdentityResponse.toEntity(response.liable())
        );

        order.getInventory().addAll(
                response.inventory().stream()
                        .map(ProductIdentityResponse::toEntity)
                        .collect(Collectors.toSet())
        );

        return order;
    }

    public static OrderResponse toResponse(Order entity) {
        return new OrderResponse(
                entity.getId().toString(),
                entity.getStatus().toString(),
                entity.getDescription(),
                entity.getRegistered(),
                AdministratorIdentityResponse.toResponse(entity.getLiable()),
                entity.getInventory().stream()
                        .map(ProductIdentityResponse::toResponse)
                        .collect(Collectors.toSet())
        );
    }

    public static OrderResponse toResponse(OrderRequest request, AdministratorIdentityResponse administrator) {
        return new OrderResponse(
                request.id(),
                request.status(),
                request.description(),
                request.registered(),
                administrator,
                request.inventory().stream()
                        .map(ProductIdentityResponse::toResponse)
                        .collect(Collectors.toSet())
        );
    }

}
