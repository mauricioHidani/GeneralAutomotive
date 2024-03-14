package com.mh.ga.administrative.repositories.adapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdministratorAdapter<ENTITY, IDENTITY> {
    ENTITY save(ENTITY entity);
    Optional<ENTITY> findById(IDENTITY id);
    ENTITY findByDocument(String document);
    Page<ENTITY> findByOffice(String office, Pageable pageable);
    Page<ENTITY> findAll(Pageable pageable);
}
