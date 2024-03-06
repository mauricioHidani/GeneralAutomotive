package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.enums.Offices;
import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.models.utils.PersonDocumentUtil;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.administrator.SaveAdministrator;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;

import java.util.UUID;

public class SaveAdministratorImpl
        implements SaveAdministrator<AdministratorRequest, AdministratorResponse> {

    private final AdministratorAdapter<Administrator, UUID> adapter;

    public SaveAdministratorImpl(AdministratorAdapter<Administrator, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public AdministratorResponse execute(AdministratorRequest request) {
        if (request == null) {
            throw new IllegalArgumentException(
                    "It was not possible to proceed this process as the information provided is not valid"
            );
        }

        if (!PersonDocumentUtil.isValid(request.document())) {
            throw new IllegalArgumentException(
                    "Unable to proceed with the invalid administrator document"
            );
        }

        if (Offices.toEnum(request.office()) == null) {
            throw new IllegalArgumentException(
                    "It is not possible to proceed with the operation, the administrator's office entered is not valid"
            );
        }

        if (adapter.findByDocument(request.document()) != null) {
            throw new IllegalArgumentException(
                    "The document provided is not valid to proceed with the request"
            );
        }

        var saved = adapter.save(AdministratorRequest.toEntity(request));
        if (saved == null) {
            throw new DataIntegrityException(
                    "It was not possible to continue with the process due to an internal error due to data not being entered"
            );
        }

        var response = AdministratorResponse.toResponse(saved);

        if (!PersonDocumentUtil.isHidden(response.document())) {
            throw new DataIntegrityException(
                    "Internal service error when trying to return the request response"
            );
        }

        return response;
    }

}
