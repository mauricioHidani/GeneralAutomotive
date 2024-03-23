package com.mh.ga.administrative.services.administrator.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import com.mh.ga.administrative.services.administrator.FindByOfficeAdministrator;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class FindByOfficeAdministratorImpl
        implements FindByOfficeAdministrator<String, Page<AdministratorResponse>> {

    private final AdministratorAdapter<Administrator, UUID> adapter;

    public FindByOfficeAdministratorImpl(AdministratorAdapter<Administrator, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Page<AdministratorResponse> execute(String officeName, Pageable pageable) {
        Page<Administrator> found;

        if (officeName == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }

        found = officeName.isBlank() ?
                adapter.findAll(pageable) :
                adapter.findByOffice(officeName, pageable)
        ;

        if (found.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Administrator not found with the requested Office name informated"
            );
        }

        return new PageImpl<>(
                found.stream().map(AdministratorResponse::toResponse).toList()
        );
    }

}
