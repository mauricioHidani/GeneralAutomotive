package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
    String id,

    @NotBlank(message = "This field is mandatory")
    @Length(min = 3, max = 128, message = "This field must contain a valid number of caracters")
    String title,

    @Length(max = 512, message = "This field must contain a valid number of characters")
    String description,

    @NotNull(message = "This field is mandatory")
    Double charge,

    @NotNull(message = "This field is mandatory")
    Double weight,

    @NotNull(message = "This field is mandatory")
    Double space,

    @NotNull(message = "This field is mandatory")
    BigDecimal unitCost
) {
    public static Product toEntity(ProductRequest request) {
        return new Product(
                request.id != null ? UUID.fromString(request.id()) : null,
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
