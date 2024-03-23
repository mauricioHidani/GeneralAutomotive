package com.mh.ga.administrative.repositories.adapter;

import java.util.Optional;

public interface OrderAdapter<ENTITY, IDENTITY> {
    boolean existingById(IDENTITY id);
    Optional<ENTITY> findById(IDENTITY id);
    ENTITY save(ENTITY entity);
    void deleteById(IDENTITY id);
}
