package com.mh.ga.administrative.models.factories;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.enums.Offices;
import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;

import java.util.UUID;

public class AdministratorFactory {

    public static Administrator createEntityByID(UUID id) {
        return new Administrator(
                id,
                "123.456.789-09",
                "Nathalia Santos",
                Offices.BUYING_COORDINATOR
        );
    }

    public static Administrator createEntityWithoutID() {
        return new Administrator(
                null,
                "123.456.789-09",
                "Nathalia Santos",
                Offices.BUYING_COORDINATOR
        );
    }

    public static AdministratorRequest createRequest() {
        return new AdministratorRequest(
                "123.456.789-09",
                "Nathalia Santos",
                Offices.BUYING_COORDINATOR.toString()

        );
    }

    public static AdministratorResponse createResponseByID(String id) {
        return new AdministratorResponse(
                id,
                "***.456.789-**",
                "Nathalia Santos",
                Offices.BUYING_COORDINATOR.toString()
        );
    }

    public static AdministratorResponse createResponseWithoudID() {
        return new AdministratorResponse(
                null,
                "***.456.789-**",
                "Nathalia Santos",
                Offices.BUYING_COORDINATOR.toString()
        );
    }

}
