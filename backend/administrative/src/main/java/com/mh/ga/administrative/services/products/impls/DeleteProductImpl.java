package com.mh.ga.administrative.services.products.impls;

import com.mh.ga.administrative.models.entities.Product;
import com.mh.ga.administrative.repositories.adapter.ProductAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import com.mh.ga.administrative.services.products.DeleteProduct;

import java.util.UUID;

public class DeleteProductImpl implements DeleteProduct<String> {

    private final ProductAdapter<Product, UUID> adapter;

    public DeleteProductImpl(ProductAdapter<Product, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public void execute(String id) {
        UUID validId;
        if (id == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }

        try {
            validId = UUID.fromString(id);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unable to proceed with the query with the requested information"
            );
        }

        if (!adapter.existingById(validId)) {
            throw new ResourceNotFoundException(
                    "Product not found with the requested information"
            );
        }

        adapter.delete(validId);
    }

}
