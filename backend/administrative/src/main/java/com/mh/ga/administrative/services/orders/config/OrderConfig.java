package com.mh.ga.administrative.services.orders.config;

import com.mh.ga.administrative.models.entities.Order;
import com.mh.ga.administrative.models.transfers.OrderRequest;
import com.mh.ga.administrative.models.transfers.OrderResponse;
import com.mh.ga.administrative.repositories.OrderRepository;
import com.mh.ga.administrative.repositories.adapter.OrderAdapter;
import com.mh.ga.administrative.repositories.adapter.impls.OrderAdapterImpl;
import com.mh.ga.administrative.services.orders.DeleteOrder;
import com.mh.ga.administrative.services.orders.FindByIdOrder;
import com.mh.ga.administrative.services.orders.SaveOrder;
import com.mh.ga.administrative.services.orders.UpdateOrder;
import com.mh.ga.administrative.services.orders.impls.DeleteOrderImpl;
import com.mh.ga.administrative.services.orders.impls.FindByIdOrderImpl;
import com.mh.ga.administrative.services.orders.impls.SaveOrderImpl;
import com.mh.ga.administrative.services.orders.impls.UpdateOrderImpl;
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
    public FindByIdOrder<String, OrderResponse> orderFindById(OrderAdapter<Order, UUID> adapter) {
        return new FindByIdOrderImpl(adapter);
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
