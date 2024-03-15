package com.mh.ga.administrative.services.products.config;

import com.mh.ga.administrative.models.entities.Product;
import com.mh.ga.administrative.models.transfers.ProductRequest;
import com.mh.ga.administrative.models.transfers.ProductResponse;
import com.mh.ga.administrative.repositories.ProductRepository;
import com.mh.ga.administrative.repositories.adapter.ProductAdapter;
import com.mh.ga.administrative.repositories.adapter.impls.ProductAdapterImpl;
import com.mh.ga.administrative.services.products.SaveProduct;
import com.mh.ga.administrative.services.products.impls.SaveProductImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ProductConfig {

    @Bean public ProductAdapter<Product, UUID> productAdapter(
            ProductRepository repository) {
        return new ProductAdapterImpl(repository);
    }

    @Bean public SaveProduct<ProductRequest, ProductResponse> productSave(
            ProductAdapter<Product, UUID> adapter) {
        return new SaveProductImpl(adapter);
    }

}
