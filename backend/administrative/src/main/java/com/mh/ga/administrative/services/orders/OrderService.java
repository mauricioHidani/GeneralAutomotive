package com.mh.ga.administrative.services.orders;

import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final SaveOrder<OrderRequest, OrderResponse> save;

    public OrderService(SaveOrder<OrderRequest, OrderResponse> save) {
        this.save = save;
    }

    @Transactional
    public OrderResponse save(OrderRequest request) {
        return save.execute(request);
    }

}
