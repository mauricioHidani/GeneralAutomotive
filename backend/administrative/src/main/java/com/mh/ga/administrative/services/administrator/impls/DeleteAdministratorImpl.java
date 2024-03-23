package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.administrator.DeleteAdministrator;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;

import java.util.UUID;

public class DeleteAdministratorImpl implements DeleteAdministrator<String> {

    private final AdministratorAdapter<Administrator, UUID> adapter;

    public DeleteAdministratorImpl(AdministratorAdapter<Administrator, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public void execute(String id) {
        UUID validId;

        if (id == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }

        try {
            validId = UUID.fromString(id);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unable to proceed with the query with the requested information"
            );
        }

        if (!adapter.existingById(validId)) {
            throw new ResourceNotFoundException(
                    "Administrator not found with the requested information"
            );
        }

        adapter.delete(validId);
    }

}
