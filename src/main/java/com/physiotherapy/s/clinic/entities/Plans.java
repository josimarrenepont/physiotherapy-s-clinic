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
    private Integer numberOfAdditionalPeople;

    @OneToMany(mappedBy = "plans")
    private List<TypeOfPlan> typeOfPlans = new ArrayList<>();
    @OneToMany(mappedBy = "plans")
    private Set<Client> clients = new HashSet<>();

    public Plans(){}

    public Plans(Long id, LocalDate moment, Double additionalPricePerson, Double price, Integer numberOfAdditionalPeople) {
        this.id = id;
        this.moment = moment;
        this.additionalPricePerson = additionalPricePerson;
        this.price = price;
        this.numberOfAdditionalPeople = numberOfAdditionalPeople;
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

    public Integer getNumberOfAdditionalPeople() {
        return numberOfAdditionalPeople;
    }

    public void setNumberOfAdditionalPeople(Integer numberOfAdditionalPeople) {
        this.numberOfAdditionalPeople = numberOfAdditionalPeople;
    }

    public Double calculateValuesIndividual(int numberOfDependents, TypeOfPlan[] typeOfPlans){
            double sum = 0.0;
            for(TypeOfPlan x : typeOfPlans){
                if(x.getNumberOfDependents() > 0) {
                    sum += x.getPrice() * numberOfDependents + getNumberOfAdditionalPeople();
                }else{
                        sum += x.getPrice();
            }
        }
      return sum;
  }
    public Double calculateValuesFamily(int numberOfDependents, TypeOfPlan[] typeOfPlans){
            double sum = 0.0;
            for(TypeOfPlan x : typeOfPlans){
                sum += x.getSubTotal() + getNumberOfAdditionalPeople();
            }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plans plans)) return false;
        return Objects.equals(getId(), plans.getId())
                && Objects.equals(getMoment(), plans.getMoment())
                && Objects.equals(getAdditionalPricePerson(), plans.getAdditionalPricePerson())
                && Objects.equals(getPrice(), plans.getPrice()) && Objects.equals(typeOfPlans, plans.typeOfPlans)
                && Objects.equals(clients, plans.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMoment(), getAdditionalPricePerson(), getPrice());
    }
}
