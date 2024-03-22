package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Administrator;
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
    AdministratorIdentityRequest liable,
    Set<ProductIdentityRequest> inventory
) {

    public static Order toEntity(OrderRequest request) {
        Order order = new Order(
                request.id() != null ? UUID.fromString(request.id()) : null,
                OrderStatus.toEnum(request.status()),
                request.description(),
                request.registered(),
                new Administrator(request.liable().id(), null, null, null)
        );

        order.getInventory().addAll(
                request.inventory().stream()
                        .map(ProductIdentityRequest::toEntity)
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
                new AdministratorIdentityRequest(entity.getId()),
                entity.getInventory().stream()
                        .map(ProductIdentityRequest::toRequest)
                        .collect(Collectors.toSet())
        );
    }

    public static OrderRequest toRequest(OrderResponse response) {
        return new OrderRequest(
                response.id(),
                response.status(),
                response.description(),
                response.registered(),
                AdministratorIdentityRequest.toRequest(response.liable()),
                response.inventory().stream()
                        .map(ProductIdentityRequest::toRequest)
                        .collect(Collectors.toSet())
        );
    }

}
