package com.mh.ga.administrative.repositories.adapter;

import java.util.Optional;

public interface ProductAdapter<ENTITY, IDENTITY> {
    ENTITY save(ENTITY entity);
    Optional<ENTITY> findById(IDENTITY id);
}
