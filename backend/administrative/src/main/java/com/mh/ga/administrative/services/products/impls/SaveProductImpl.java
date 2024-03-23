package com.mh.ga.administrative.services.products.impls;

import com.mh.ga.administrative.models.entities.Product;
import com.mh.ga.administrative.models.transfers.ProductRequest;
import com.mh.ga.administrative.models.transfers.ProductResponse;
import com.mh.ga.administrative.repositories.adapter.ProductAdapter;
import com.mh.ga.administrative.services.exceptions.DataIntegrityException;
import com.mh.ga.administrative.services.products.SaveProduct;

import java.util.UUID;

public class SaveProductImpl implements SaveProduct<ProductRequest, ProductResponse> {

    private final ProductAdapter<Product, UUID> adapter;

    public SaveProductImpl(ProductAdapter<Product, UUID> adapter) {
        this.adapter = adapter;
    }

    @Override
    public ProductResponse execute(ProductRequest product) {
        if (product == null) {
            throw new IllegalArgumentException(
                    "Unable to proceed with the request"
            );
        }

        Product saved = adapter.save(ProductRequest.toEntity(product));
        if (saved == null) {
            throw new DataIntegrityException(
                    "It was not possible to continue executing the operation"
            );
        }
        return ProductResponse.toResponse(saved);
    }

}
