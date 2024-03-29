package com.mh.ga.administrative.models.entities;

import com.mh.ga.administrative.models.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = -6000799533832033236L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID id;

    @Enumerated(EnumType.STRING)
    @Column(length = 128)
    private final OrderStatus status;

    @Column(columnDefinition = "TEXT")
    private final String description;

    @Temporal(TemporalType.TIMESTAMP)
    private final Instant registered;

    @ManyToOne
    @JoinColumn(name = "liable_id")
    private final Administrator liable;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "inventory",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private final Set<Product> inventory = new HashSet<>();

    protected Order() {
        this.id = null;
        this.status = null;
        this.description = null;
        this.registered = null;
        this.liable = null;
    }

    public Order(OrderStatus status,
                 String description,
                 Instant registered,
                 Administrator liable
    ) {
        this.id = null;
        this.status = status;
        this.description = description;
        this.registered = registered;
        this.liable = liable;
    }

    public Order(
            UUID id,
            OrderStatus status,
            String description,
            Instant registered,
            Administrator liable
    ) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.registered = registered;
        this.liable = liable;
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Instant getRegistered() {
        return registered;
    }

    public Administrator getLiable() {
        return liable;
    }

    public Set<Product> getInventory() {
        return inventory;
    }

    public void addProduct(Product product) {
        this.inventory.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
               status == order.status &&
               Objects.equals(registered, order.registered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                status,
                registered
        );
    }

}
