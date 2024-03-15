package com.mh.ga.administrative.services.products;

import com.mh.ga.administrative.models.transfers.ProductRequest;
import com.mh.ga.administrative.models.transfers.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductService {

    private final SaveProduct<ProductRequest, ProductResponse> save;
    private final FindByIdProduct<String, ProductResponse> findById;

    public ProductService(
            SaveProduct<ProductRequest, ProductResponse> save,
            FindByIdProduct<String, ProductResponse> findById) {
        this.save = save;
        this.findById = findById;
    }

    @Transactional
    public ProductResponse save(ProductRequest request) {
        return save.execute(request);
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(String id) {
        return findById.execute(id);
    }

}
