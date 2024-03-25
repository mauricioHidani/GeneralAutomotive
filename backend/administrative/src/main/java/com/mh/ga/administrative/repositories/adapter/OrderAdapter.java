package com.mh.ga.administrative.repositories.adapter;

import ch.qos.logback.core.status.InfoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Optional;

public interface OrderAdapter<ENTITY, IDENTITY> {
    boolean existingById(IDENTITY id);
    Optional<ENTITY> findById(IDENTITY id);
    Page<ENTITY> findAll(Pageable pageable);
    Page<ENTITY> findByRegistered(Instant start, Instant end, Pageable pageable);
    ENTITY save(ENTITY entity);
    void deleteById(IDENTITY id);
}
