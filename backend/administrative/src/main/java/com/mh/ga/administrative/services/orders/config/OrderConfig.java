package com.mh.ga.administrative.services.orders.config;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.OrderRepository;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.repositories.adapter.impls.OrderAdapterImpl;
import com.mh.ga.administrative.services.orders.FindByIdOrder;
import com.mh.ga.administrative.services.orders.SaveOrder;
import com.mh.ga.administrative.services.orders.impls.FindByIdOrderImpl;
import com.mh.ga.administrative.services.orders.impls.SaveOrderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class OrderConfig {

    @Bean
    public OrderAdapter<Order, UUID> orderAdapter(OrderRepository repository) {
        return new OrderAdapterImpl(repository);
    }

    @Bean
    public FindByIdOrder<UUID, OrderResponse> orderFindById(OrderAdapter<Order, UUID> adapter) {
        return new FindByIdOrderImpl(adapter);
    }

    @Bean
    public SaveOrder<OrderRequest, OrderResponse> orderSave(OrderAdapter<Order, UUID> adapter) {
        return new SaveOrderImpl(adapter);
    }

}
