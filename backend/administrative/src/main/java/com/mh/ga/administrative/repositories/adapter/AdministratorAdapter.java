package com.mh.ga.administrative.repositories.adapter;

import java.util.Optional;

public interface AdministratorAdapter<ENTITY, IDENTITY> {
    ENTITY save(ENTITY entity);
    Optional<ENTITY> findById(IDENTITY id);
    ENTITY findByDocument(String document);
}
