package com.mh.ga.administrative.repositories.adapter;

public interface OrderAdapter<ENTITY, IDENTITY> {
    ENTITY save(ENTITY entity);
}
