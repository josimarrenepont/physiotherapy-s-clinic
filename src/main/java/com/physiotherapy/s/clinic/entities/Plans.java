package com.physiotherapy.s.clinic.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tb_plans")
public class Plans implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate moment;
    private Double additionalPricePerson;
    private Double price;

    @OneToMany(mappedBy = "plans")
    private Set<Client> clients = new HashSet<>();

    public Plans(){}

    public Plans(Long id, LocalDate moment, Double additionalPricePerson, Double price) {
        this.id = id;
        this.moment = moment;
        this.additionalPricePerson = additionalPricePerson;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMoment() {
        return moment;
    }

    public void setMoment(LocalDate moment) {
        this.moment = moment;
    }

    public Double getAdditionalPricePerson() {
        return additionalPricePerson;
    }

    public void setAdditionalPricePerson(Double additionalPricePerson) {
        this.additionalPricePerson = additionalPricePerson;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotalPlans(){
        if(this.additionalPricePerson == null){
            return 0.0;
        }
        return this.price + this.additionalPricePerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plans plans)) return false;
        return Objects.equals(getId(), plans.getId())
                && Objects.equals(getMoment(), plans.getMoment())
                && Objects.equals(getAdditionalPricePerson(), plans.getAdditionalPricePerson())
                && Objects.equals(getPrice(), plans.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMoment(), getAdditionalPricePerson(), getPrice());
    }
}