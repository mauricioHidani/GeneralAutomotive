package com.mh.ga.administrative.models.transfers;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.enums.Offices;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AdministratorRequest(
    @NotBlank(message = "This field is mandatory")
    String document,

    @NotBlank(message = "This field is mandatory")
    @Length(min = 2, max = 128, message = "This field must contain a valid number of caracters")
    String fullName,

    @NotBlank(message = "This field is mandatory")
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
