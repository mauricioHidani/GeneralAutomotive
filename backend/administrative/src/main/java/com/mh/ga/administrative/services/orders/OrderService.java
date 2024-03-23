package com.mh.ga.administrative.services.orders;

import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    private final FindByIdOrder<UUID, OrderResponse> findById;
    private final SaveOrder<OrderRequest, OrderResponse> save;

    public OrderService(FindByIdOrder<UUID, OrderResponse> findById,
                        SaveOrder<OrderRequest, OrderResponse> save) {
        this.findById = findById;
        this.save = save;
    }

    @Transactional(readOnly = true)
    public OrderResponse findById(UUID id) {
        return findById.execute(id);
    }

    @Transactional
    public OrderResponse save(OrderRequest request) {
        return save.execute(request);
    }

}
