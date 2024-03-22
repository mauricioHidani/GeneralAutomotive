package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Product;

import java.util.UUID;

public record ProductIdentityRequest(
        UUID id
) {
    public static Product toEntity(ProductIdentityRequest request) {
        return new Product(
                request.id(),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static ProductIdentityRequest toRequest(Product entity) {
        return new ProductIdentityRequest(
                entity.getId()
        );
    }

    public static ProductIdentityRequest toRequest(ProductIdentityResponse response) {
        return new ProductIdentityRequest(
                response.id()
        );
    }
}
