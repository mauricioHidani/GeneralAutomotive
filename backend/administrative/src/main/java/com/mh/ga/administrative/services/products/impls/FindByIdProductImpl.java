package com.mh.ga.administrative.services.products.impls;

import com.mh.ga.administrative.models.entities.Administrator;
import com.mh.ga.administrative.models.entities.Product;
import com.mh.ga.administrative.models.transfers.AdministratorResponse;
import com.mh.ga.administrative.models.transfers.ProductResponse;
import com.mh.ga.administrative.repositories.adapter.ProductAdapter;
import com.mh.ga.administrative.services.exceptions.ResourceNotFoundException;
import com.mh.ga.administrative.services.products.FindByIdProduct;

import java.util.UUID;

public class FindByIdProductImpl implements FindByIdProduct<String, ProductResponse> {

    private final ProductAdapter<Product, UUID> adapter;

    public FindByIdProductImpl(ProductAdapter<Product, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override public ProductResponse execute(String id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Operation not valid on the requested system"
            );
        }

        try {
            UUID validId = UUID.fromString(id);
            Product found = adapter.findById(validId)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product not found with the requested information"
                    ));
            return ProductResponse.toResponse(found);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Unable to proceed with the query with the requested information"
            );
        }
    }

}
