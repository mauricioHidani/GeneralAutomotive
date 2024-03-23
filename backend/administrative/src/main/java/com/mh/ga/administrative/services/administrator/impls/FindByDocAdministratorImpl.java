package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.models.utils.PersonDocumentUtil;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.administrator.FindByDocAdministrator;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;

import java.util.UUID;

public class FindByDocAdministratorImpl implements FindByDocAdministrator<String, AdministratorResponse> {

    private final AdministratorAdapter<Administrator, UUID> adapter;

    public FindByDocAdministratorImpl(AdministratorAdapter<Administrator, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public AdministratorResponse execute(String document) {
        if (document == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }

        if (!PersonDocumentUtil.isValid(document)) {
            throw new IllegalArgumentException(
                    "It is not possible to proceed with the operation with the informed document"
            );
        }

        Administrator found = adapter.findByDocument(document);
        if (found == null) {
            throw new ResourceNotFoundException(
                    "An Administrator was not found with the specified document"
            );
        }

        return AdministratorResponse.toResponse(found);
    }

}
