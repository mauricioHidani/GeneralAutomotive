package com.mh.ga.administrative.repositories.adapter;

public interface AdministratorAdapter<ENTITY, IDENTITY> {
    ENTITY save(ENTITY entity);
    ENTITY findByDocument(String document);
}
