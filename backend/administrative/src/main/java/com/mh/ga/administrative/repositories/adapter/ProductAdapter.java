package com.mh.ga.administrative.repositories.adapter;

import java.util.Optional;

public interface ProductAdapter<ENTITY, IDENTITY> {
    ENTITY save(ENTITY entity);
    boolean existingById(IDENTITY id);
    Optional<ENTITY> findById(IDENTITY id);
    void delete(IDENTITY id);
}
