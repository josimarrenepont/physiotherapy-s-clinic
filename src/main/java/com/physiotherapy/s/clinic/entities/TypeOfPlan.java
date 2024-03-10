package com.physiotherapy.s.clinic.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_type_of_plan")
public class TypeOfPlan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
    private Integer numberOfDependents;
    private Double individualPlan;
    private Double familyPlan;

    @ManyToOne
    @JoinColumn(name = "id_plans")
    private Plans plans;

    public TypeOfPlan(){}

    public TypeOfPlan(Long id, Double price, Integer numberOfDependents, Double individualPlan, Double familyPlan) {
        this.id = id;
        this.price = price;
        this.numberOfDependents = numberOfDependents;
        this.individualPlan = individualPlan;
        this.familyPlan = familyPlan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumberOfDependents() {
        return numberOfDependents;
    }

    public void setNumberOfDependents(Integer numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public Double getIndividualPlan() {
        return individualPlan;
    }

    public void setIndividualPlan(Double individualPlan) {
        this.individualPlan = individualPlan;
    }

    public Double getFamilyPlan() {
        return familyPlan;
    }

    public void setFamilyPlan(Double familyPlan) {
        this.familyPlan = familyPlan;
    }
    public Double getSubTotal(){
        return price * numberOfDependents;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeOfPlan that)) return false;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getPrice(), that.getPrice())
                && Objects.equals(getNumberOfDependents(), that.getNumberOfDependents())
                && Objects.equals(getIndividualPlan(), that.getIndividualPlan())
                && Objects.equals(getFamilyPlan(), that.getFamilyPlan())
                && Objects.equals(plans, that.plans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice(), getNumberOfDependents(), getIndividualPlan(), getFamilyPlan(), plans);
    }
}
