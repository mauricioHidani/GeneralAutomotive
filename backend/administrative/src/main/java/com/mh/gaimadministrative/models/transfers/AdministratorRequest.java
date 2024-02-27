package com.mh.gaimadministrative.models.transfers;

import com.mh.gaimadministrative.models.entities.Administrator;
import com.mh.gaimadministrative.models.enums.Offices;

import java.util.UUID;

public record AdministratorRequest(
    String id,
    String document,
    String fullName,
    String office
) {
    public static Administrator toEntity(AdministratorRequest request) {
        return new Administrator(
                UUID.fromString(request.id()),
                request.document(),
                request.fullName(),
                Offices.toEnum(request.office())
        );
    }

    public static AdministratorRequest toRequest(Administrator entity) {
        return new AdministratorRequest(
                entity.getId().toString(),
                entity.getDocument(),
                entity.getFullName(),
                entity.getOffice().toString()
        );
    }

    public static AdministratorRequest toRequest(AdministratorResponse response) {
        return new AdministratorRequest(
                response.id(),
                response.document(),
                response.fullName(),
                response.office()
        );
    }
}
