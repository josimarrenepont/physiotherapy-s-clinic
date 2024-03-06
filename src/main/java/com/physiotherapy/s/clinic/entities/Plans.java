package com.physiotherapy.s.clinic.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Plans implements Serializable {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate moment;
    private Double individualPlan;
    private Double familyPlan;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plans plans)) return false;
        return Objects.equals(getId(), plans.getId()) && Objects.equals(getMoment(), plans.getMoment()) && Objects.equals(getIndividualPlan(), plans.getIndividualPlan()) && Objects.equals(getFamilyPlan(), plans.getFamilyPlan());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMoment(), getIndividualPlan(), getFamilyPlan());
    }
}
