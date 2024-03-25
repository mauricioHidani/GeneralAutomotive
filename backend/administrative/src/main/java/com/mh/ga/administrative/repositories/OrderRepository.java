package com.mh.ga.administrative.repositories;

import com.mh.ga.administrative.models.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Page<Order> findByRegisteredBetween(Instant start, Instant end, Pageable pageable);
}
