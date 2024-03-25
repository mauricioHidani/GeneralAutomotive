package com.mh.ga.administrative.services.orders;

import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class OrderService {

    private final FindByIdOrder<String, OrderResponse> findById;
    private final FindByRegisterOrder<Map<String, String>, Pageable, Page<OrderResponse>> findByRegister;
    private final SaveOrder<OrderRequest, OrderResponse> save;
    private final UpdateOrder<OrderRequest, String, OrderResponse> update;
    private final DeleteOrder<String> delete;

    public OrderService(FindByIdOrder<String, OrderResponse> findById,
                        FindByRegisterOrder<Map<String, String>, Pageable, Page<OrderResponse>> findByRegister,
                        SaveOrder<OrderRequest, OrderResponse> save,
                        UpdateOrder<OrderRequest, String, OrderResponse> update,
                        DeleteOrder<String> delete) {
        this.findById = findById;
        this.findByRegister = findByRegister;
        this.save = save;
        this.update = update;
        this.delete = delete;
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(String id) {
        return findById.execute(id);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponse> findByRegister(Map<String, String> registers, Pageable pageable) {
        return findByRegister.execute(registers, pageable);
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
