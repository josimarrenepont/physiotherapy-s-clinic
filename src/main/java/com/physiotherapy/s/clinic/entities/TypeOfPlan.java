package com.physiotherapy.s.clinic.entities;

import com.physiotherapy.s.clinic.entities.pk.OrderPlanPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_type_of_plan")
public class TypeOfPlan implements Serializable {
    @EmbeddedId
    private OrderPlanPK id = new OrderPlanPK();
    private Double price;
    private Integer quantity;

    public TypeOfPlan(){}

    public TypeOfPlan(OrderPlanPK id, Double price, Integer quantity) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderPlanPK getId() {
        return id;
    }

    public void setId(OrderPlanPK id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeOfPlan that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPrice(), that.getPrice()) && Objects.equals(getQuantity(), that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice(), getQuantity());
    }
}