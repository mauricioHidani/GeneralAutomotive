package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.administrator.FindByIdAdministrator;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;

import java.util.UUID;

public class FindByIdAdministratorImpl implements FindByIdAdministrator<String, AdministratorResponse> {

    private final AdministratorAdapter<Administrator, UUID> adapter;

    public FindByIdAdministratorImpl(AdministratorAdapter<Administrator, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public AdministratorResponse execute(String id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }

        try {
            UUID validId = UUID.fromString(id);
            Administrator found = adapter.findById(validId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Administrator not found with the requested information"
                    ));
            return AdministratorResponse.toResponse(found);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unable to proceed with the query with the requested information"
            );
        }
    }

}
