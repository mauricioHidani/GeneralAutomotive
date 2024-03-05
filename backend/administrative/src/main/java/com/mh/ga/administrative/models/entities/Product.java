package com.mh.ga.administrative.models.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 4221910545851338453L;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Double charge;

    @Column
    private Double weight;

    @Column
    private Double space;

    @Column
    private BigDecimal unitCost;

    protected Product() {
    }

    public Product(String title, String description, Double charge, Double weight, Double space, BigDecimal unitCost) {
        this.title = title;
        this.description = description;
        this.charge = charge;
        this.weight = weight;
        this.space = space;
        this.unitCost = unitCost;
    }

    public Product(UUID id, String title, String description, Double charge, Double weight, Double space,
                   BigDecimal unitCost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.charge = charge;
        this.weight = weight;
        this.space = space;
        this.unitCost = unitCost;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getCharge() {
        return charge;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getSpace() {
        return space;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
               Objects.equals(title, product.title) &&
               Objects.equals(description, product.description) &&
               Objects.equals(charge, product.charge) &&
               Objects.equals(unitCost, product.unitCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                title,
                description,
                charge,
                unitCost
        );
    }

}
