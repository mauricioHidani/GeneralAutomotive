package com.mh.ga.administrative.repositories.adapter.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.repositories.AdministratorRepository;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;

import java.util.UUID;

public class AdministratorAdapterImpl implements AdministratorAdapter<Administrator, UUID> {

    private final AdministratorRepository repository;

    public AdministratorAdapterImpl(AdministratorRepository repository) {
        this.repository = repository;
    }

    @Override public Administrator save(Administrator entity) {
        return repository.save(entity);
    }

    @Override public Administrator findByDocument(String document) {
        return null;
    }

}
