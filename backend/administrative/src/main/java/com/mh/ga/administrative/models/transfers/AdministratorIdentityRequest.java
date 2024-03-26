package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Administrator;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AdministratorIdentityRequest(
        @NotNull(message = "This field is mandatory")
        UUID id
) {
    public static Administrator toEntity(AdministratorIdentityRequest request) {
        return new Administrator(
                request.id(),
                null,
                null,
                null
        );
    }

    public static AdministratorIdentityRequest toRequest(Administrator entity) {
        return new AdministratorIdentityRequest(
                entity.getId()
        );
    }

    public static AdministratorIdentityRequest toRequest(AdministratorIdentityResponse response) {
        return new AdministratorIdentityRequest(
                response.id()
        );
    }
}
