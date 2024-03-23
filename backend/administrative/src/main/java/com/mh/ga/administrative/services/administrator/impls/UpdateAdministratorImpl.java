package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.enums.Offices;
import com.mh.ga.administrative.models.transfers.AdministratorRequest;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.models.utils.PersonDocumentUtil;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.administrator.UpdateAdministrator;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;

import java.util.UUID;

public class UpdateAdministratorImpl
        implements UpdateAdministrator<String, AdministratorRequest, AdministratorResponse> {

    private final AdministratorAdapter<Administrator, UUID> adapter;

    public UpdateAdministratorImpl(AdministratorAdapter<Administrator, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public AdministratorResponse execute(String id, AdministratorRequest request) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }
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

        UUID validId;

        try {
            validId = UUID.fromString(id);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unable to proceed with the query with the requested information"
            );
        }

        String document = request.document();
        Administrator found = adapter.findById(validId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Administrator not found with the requested information"
                ));

        Administrator findByDocument = adapter.findByDocument(document);
        if (findByDocument != null && !findByDocument.getId().equals(validId)) {
            throw new IllegalArgumentException(
                    "The document provided is not valid to proceed with the request"
            );
        }

        Administrator updated = adapter.save(
                copyFields(found, request)
        );

        AdministratorResponse result = AdministratorResponse.toResponse(updated);
        if (!PersonDocumentUtil.isHidden(result.document())) {
            throw new DataIntegrityException(
                    "Internal service error when trying to return the request response"
            );
        }

        return result;
    }

    private Administrator copyFields(Administrator found, AdministratorRequest update) {
        return new Administrator(
                found.getId(),
                update.document() != null ? update.document() : found.getDocument(),
                update.fullName() != null ? update.fullName() : found.getFullName(),
                update.office() != null ? Offices.toEnum(update.office()) : found.getOffice()
        );
    }

}
