package com.mh.ga.administrative.repositories.adapter.impls;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.repositories.OrderRepository;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;

import java.util.Optional;
import java.util.UUID;

public class OrderAdapterImpl implements OrderAdapter<Order, UUID> {

    private final OrderRepository repository;

    public OrderAdapterImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existingById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
