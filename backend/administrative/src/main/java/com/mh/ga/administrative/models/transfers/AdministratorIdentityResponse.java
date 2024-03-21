package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Administrator;

import java.util.UUID;

public record AdministratorIdentityResponse(
        UUID id
) {
    public static Administrator toEntity(AdministratorIdentityResponse response) {
        return new Administrator(
                response.id(),
                null,
                null,
                null
        );
    }

    public static AdministratorIdentityResponse toResponse(Administrator entity) {
        return new AdministratorIdentityResponse(
                entity.getId()
        );
    }

    public static AdministratorIdentityResponse toResponse(AdministratorIdentityRequest request) {
        return new AdministratorIdentityResponse(
                request.id()
        );
    }
}
