package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
    String id,
    String title,
    String description,
    Double charge,
    Double weight,
    Double space,
    BigDecimal unitCost
) {
    public static Product toEntity(ProductResponse response) {
        return new Product(
                UUID.fromString(response.id()),
                response.title(),
                response.description(),
                response.charge(),
                response.weight(),
                response.space(),
                response.unitCost()
        );
    }

    public static ProductResponse toResponse(Product entity) {
        return new ProductResponse(
                entity.getId().toString(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getCharge(),
                entity.getWeight(),
                entity.getSpace(),
                entity.getUnitCost()
        );
    }

    public static ProductResponse toResponse(ProductRequest request) {
        return new ProductResponse(
                request.id(),
                request.title(),
                request.description(),
                request.charge(),
                request.weight(),
                request.space(),
                request.unitCost()
        );
    }
}
