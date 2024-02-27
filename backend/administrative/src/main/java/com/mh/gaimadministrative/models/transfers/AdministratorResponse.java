package com.mh.gaimadministrative.models.transfers;

import com.mh.gaimadministrative.models.entities.Administrator;
import com.mh.gaimadministrative.models.enums.Offices;
import com.mh.gaimadministrative.models.utils.PersonDocumentUtil;

import java.util.UUID;

public record AdministratorResponse(
    String id,
    String document,
    String fullName,
    String office
) {
    public static Administrator toEntity(AdministratorResponse response) {
        return new Administrator(
                UUID.fromString(response.id()),
                response.document(),
                response.fullName(),
                Offices.toEnum(response.office())
        );
    }

    public static AdministratorResponse toResponse(Administrator entity) {
        return new AdministratorResponse(
                entity.getId().toString(),
                PersonDocumentUtil.toHide(entity.getDocument()),
                entity.getFullName(),
                entity.getOffice().toString()
        );
    }

    public static AdministratorResponse toResponse(AdministratorRequest request) {
        return new AdministratorResponse(
                request.id(),
                PersonDocumentUtil.toHide(request.document()),
                request.fullName(),
                request.office()
        );
    }
}
