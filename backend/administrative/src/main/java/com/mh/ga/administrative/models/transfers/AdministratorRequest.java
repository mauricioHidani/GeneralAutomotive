package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.enums.Offices;

public record AdministratorRequest(
    String document,
    String fullName,
    String office
) {

    public static Administrator toEntity(AdministratorRequest request) {
        return new Administrator(
                request.document(),
                request.fullName(),
                Offices.toEnum(request.office())
        );
    }

    public static AdministratorRequest toRequest(Administrator entity) {
        return new AdministratorRequest(
                entity.getDocument(),
                entity.getFullName(),
                entity.getOffice().toString()
        );
    }

    public static AdministratorRequest toRequest(AdministratorResponse response) {
        return new AdministratorRequest(
                response.document(),
                response.fullName(),
                response.office()
        );
    }

}
