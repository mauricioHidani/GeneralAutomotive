package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Product;

import java.util.UUID;

public record ProductIdentityResponse(
        UUID id
) {
    public static Product toEntity(ProductIdentityResponse response) {
        return new Product(
                response.id(),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static ProductIdentityResponse toResponse(Product entity) {
        return new ProductIdentityResponse(
                entity.getId()
        );
    }

    public static ProductIdentityResponse toResponse(ProductIdentityRequest request) {
        return new ProductIdentityResponse(
                request.id()
        );
    }
}
