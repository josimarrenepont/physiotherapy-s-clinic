package com.physiotherapy.s.clinic.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_dependents")
public class Dependents implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String telephone;
    private String kinship;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client clients;

    @ManyToOne
    @JoinColumn(name = "plans_id")
    private Plans plans;

    public Dependents(){}

    public Dependents(Long id, String name, String telephone, String kinship) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.kinship = kinship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getKinship() {
        return kinship;
    }

    public void setKinship(String kinship) {
        this.kinship = kinship;
    }

    public Double calculateValuesPlans(Client client){
        double sum = 0.0;
        Set<Plans> plans = client.getPlans();
        for(Plans x : plans){
            double planPrice = x.getSubTotalPlans();
            if(clients.getTotalNumberOfDependents() > 1) {
                planPrice += (clients.getTotalNumberOfDependents() - 1) * x.getAdditionalPricePerson();
            }else{
                sum += planPrice;
            }
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependents that)) return false;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getKinship(), that.getKinship());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getKinship());
    }

    public int getNumberOfDependents() {
        return clients.getTotalNumberOfDependents();
    }
}
