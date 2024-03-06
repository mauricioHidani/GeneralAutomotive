package com.mh.ga.administrative.repositories;

import com.mh.ga.administrative.models.entities.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, UUID> {
    Administrator findByDocument(String document);
}
