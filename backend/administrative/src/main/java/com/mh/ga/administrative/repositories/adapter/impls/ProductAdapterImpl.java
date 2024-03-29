package com.mh.ga.administrative.repositories.adapter.impls;

import com.mh.ga.administrative.models.entities.Product;
import com.mh.ga.administrative.repositories.ProductRepository;
import com.mh.ga.administrative.repositories.adapter.ProductAdapter;

import java.util.Optional;
import java.util.UUID;

public class ProductAdapterImpl implements ProductAdapter<Product, UUID> {

    private final ProductRepository repository;

    public ProductAdapterImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public boolean existingById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
