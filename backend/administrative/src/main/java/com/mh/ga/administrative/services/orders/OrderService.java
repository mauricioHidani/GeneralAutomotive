package com.mh.ga.administrative.services.orders;

import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    private final FindByIdOrder<String, OrderResponse> findById;
    private final SaveOrder<OrderRequest, OrderResponse> save;
    private final UpdateOrder<OrderRequest, String, OrderResponse> update;
    private final DeleteOrder<String> delete;

    public OrderService(FindByIdOrder<String, OrderResponse> findById,
                        SaveOrder<OrderRequest, OrderResponse> save,
                        UpdateOrder<OrderRequest, String, OrderResponse> update,
                        DeleteOrder<String> delete) {
        this.findById = findById;
        this.save = save;
        this.update = update;
        this.delete = delete;
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(String id) {
        return findById.execute(id);
    }

    @Transactional
    public OrderResponse save(OrderRequest request) {
        return save.execute(request);
    }

    @Transactional
    public OrderResponse update(String id, OrderRequest request) {
        return update.execute(id, request);
    }

    @Transactional
    public void delete(String id) {
        delete.execute(id);
    }

}
