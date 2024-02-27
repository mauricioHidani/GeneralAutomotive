package com.mh.gaimadministrative.models.transfers;

import com.mh.gaimadministrative.models.entities.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
    String id,
    String title,
    String description,
    Double charge,
    Double weight,
    Double space,
    BigDecimal unitCost
) {
    public static Product toEntity(ProductRequest request) {
        return new Product(
                UUID.fromString(request.id()),
                request.title(),
                request.description(),
                request.charge(),
                request.weight(),
                request.space(),
                request.unitCost()
        );
    }

    public static ProductRequest toRequest(Product entity) {
        return new ProductRequest(
                entity.getId().toString(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getCharge(),
                entity.getWeight(),
                entity.getSpace(),
                entity.getUnitCost()
        );
    }

    public static ProductRequest toRequest(ProductResponse response) {
        return new ProductRequest(
                response.id(),
                response.title(),
                response.description(),
                response.charge(),
                response.weight(),
                response.space(),
                response.unitCost()
        );
    }
}
