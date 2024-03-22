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

    public static Administrator createNewEntityByID(UUID id) {
        return new Administrator(
                id,
                "123.456.789-09",
                "New Nathalia Santos",
                Offices.BUYING_COORDINATOR
        );
    }

    public static Administrator createNewEntityWithoutID() {
        return new Administrator(
                null,
                "123.456.789-09",
                "New Nathalia Santos",
                Offices.BUYING_COORDINATOR
        );
    }

    public static Administrator createEntityByIDWithDocument(UUID id, String document) {
        return new Administrator(
                id,
                document,
                "Fulano de Almeida",
                Offices.LOGISTICS_ADMINISTRATOR
        );
    }

    public static AdministratorRequest createRequest() {
        return new AdministratorRequest(
                "123.456.789-09",
                "Nathalia Santos",
                Offices.BUYING_COORDINATOR.toString()

        );
    }

    public static AdministratorRequest createNewRequest() {
        return new AdministratorRequest(
                "123.456.789-09",
                "New Nathalia Santos",
                Offices.BUYING_COORDINATOR.toString()

        );
    }

    public static AdministratorRequest createRequestWithDocument(String document) {
        return new AdministratorRequest(
                document,
                "Fulano de Almeida",
                Offices.LOGISTICS_ADMINISTRATOR.toString()
        );
    }

    public static AdministratorResponse createResponseByID(UUID id) {
        return new AdministratorResponse(
                id.toString(),
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

    public static AdministratorResponse createNewResponseByID(UUID id) {
        return new AdministratorResponse(
                id.toString(),
                "***.456.789-**",
                "New Nathalia Santos",
                Offices.BUYING_COORDINATOR.toString()
        );
    }

    public static AdministratorResponse createNewResponseWithoudID() {
        return new AdministratorResponse(
                null,
                "***.456.789-**",
                "New Nathalia Santos",
                Offices.BUYING_COORDINATOR.toString()
        );
    }

}
