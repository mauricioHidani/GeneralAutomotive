package com.mh.ga.administrative.services.orders.config;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.OrderRepository;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.repositories.adapter.impls.OrderAdapterImpl;
import com.mh.ga.administrative.services.orders.impls.FindByRegisterOrderImpl;
import com.mh.ga.administrative.services.orders.*;
import com.mh.ga.administrative.services.orders.impls.DeleteOrderImpl;
import com.mh.ga.administrative.services.orders.impls.FindByIdOrderImpl;
import com.mh.ga.administrative.services.orders.impls.SaveOrderImpl;
import com.mh.ga.administrative.services.orders.impls.UpdateOrderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Configuration
public class OrderConfig {

    @Bean
    public OrderAdapter<Order, UUID> orderAdapter(OrderRepository repository) {
        return new OrderAdapterImpl(repository);
    }

    @Bean
    public FindByIdOrder<String, OrderResponse> orderFindById(OrderAdapter<Order, UUID> adapter) {
        return new FindByIdOrderImpl(adapter);
    }

    @Bean
    public FindByRegisterOrder<Map<String, String>, Pageable, Page<OrderResponse>> orderFindByRegister(
            OrderAdapter<Order, UUID> adapter) {
        return new FindByRegisterOrderImpl(adapter);
    }

    @Bean
    public SaveOrder<OrderRequest, OrderResponse> orderSave(OrderAdapter<Order, UUID> adapter) {
        return new SaveOrderImpl(adapter);
    }

    @Bean
    public UpdateOrder<OrderRequest, String, OrderResponse> orderUpdate(OrderAdapter<Order, UUID> adapter) {
        return new UpdateOrderImpl(adapter);
    }

    @Bean
    public DeleteOrder<String> orderDelete(OrderAdapter<Order, UUID> adapter) {
        return new DeleteOrderImpl(adapter);
    }

}
