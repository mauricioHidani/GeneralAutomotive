package com.mh.ga.administrative.services.products;

import com.mh.ga.administrative.models.transfers.ProductRequest;
import com.mh.ga.administrative.models.transfers.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final SaveProduct<ProductRequest, ProductResponse> save;

    public ProductService(SaveProduct<ProductRequest, ProductResponse> save) {
        this.save = save;
    }

    @Transactional
    public ProductResponse save(ProductRequest request) {
        return save.execute(request);
    }

}
