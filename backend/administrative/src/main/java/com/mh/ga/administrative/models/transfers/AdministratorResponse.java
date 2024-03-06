package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.enums.Offices;
import com.mh.ga.administrative.models.utils.PersonDocumentUtil;

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

    public static AdministratorResponse toResponse(AdministratorRequest request, String id) {
        return new AdministratorResponse(
                id,
                PersonDocumentUtil.toHide(request.document()),
                request.fullName(),
                request.office()
        );
    }
}
