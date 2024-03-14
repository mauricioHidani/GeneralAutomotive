package com.mh.ga.administrative.repositories.adapter.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.enums.Offices;
import com.mh.ga.administrative.repositories.AdministratorRepository;
import com.mh.ga.administrative.repositories.adapter.AdministratorAdapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public class AdministratorAdapterImpl implements AdministratorAdapter<Administrator, UUID> {

    private final AdministratorRepository repository;

    public AdministratorAdapterImpl(AdministratorRepository repository) {
        this.repository = repository;
    }

    @Override public Administrator save(Administrator entity) {
        return repository.save(entity);
    }

    @Override public Optional<Administrator> findById(UUID id) {
        return repository.findById(id);
    }

    @Override public Administrator findByDocument(String document) {
        return repository.findByDocument(document);
    }

    @Override public Page<Administrator> findByOffice(String office, Pageable pageable) {
        return repository.findByOffice(
                Offices.toEnum(office),
                pageable
        );
    }

    @Override
    public Page<Administrator> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
