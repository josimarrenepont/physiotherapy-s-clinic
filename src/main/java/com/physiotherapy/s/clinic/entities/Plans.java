package com.physiotherapy.s.clinic.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_plans")
public class Plans implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate moment;
    private Double individualPlan;
    private Double familyPlan;

    @OneToMany(mappedBy = "plans")
    private Set<Client> clients = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "id.plans")
    private Set<TypeOfPlan> typeOfPlans = new HashSet<>();

    public Plans(){}

    public Plans(Long id, Double individualPlan, Double familyPlan, LocalDate moment) {
        this.id = id;
        this.individualPlan = individualPlan;
        this.familyPlan = familyPlan;
        this.moment = moment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getMoment() {
        return moment;
    }

    public void setMoment(LocalDate moment) {
        this.moment = moment;
    }

  public Double calculateValuesIndividual(int numberOfDependents){
            double sum = 0.0;
            for(TypeOfPlan x : typeOfPlans){
                if(x.getNumberOfDependents() > 0) {
                    sum += x.getPrice() * numberOfDependents;
                }else{
                        sum += x.getPrice();
            }
        }
      return sum;
  }
    public Double calculateValuesFamily(int numberOfDependents){
            double sum = 0.0;
            for(TypeOfPlan x : typeOfPlans){
                sum += x.getSubTotal();
            }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plans plans)) return false;
        return Objects.equals(getId(), plans.getId()) && Objects.equals(getMoment(), plans.getMoment())
                && Objects.equals(getIndividualPlan(), plans.getIndividualPlan())
                && Objects.equals(getFamilyPlan(), plans.getFamilyPlan());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMoment(), getIndividualPlan(), getFamilyPlan());
    }
}
