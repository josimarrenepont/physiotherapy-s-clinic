package com.physiotherapy.s.clinic.entities;

import jakarta.persistence.*;
import org.hibernate.boot.model.internal.BinderHelper;

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
    private String cpf;
    @ManyToOne
    private Client clients;

    @ManyToOne
    private Plans plans;

    public Dependents(){}

    public Dependents(Long id, String name, String cpf, String telephone, String kinship) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public void setClient(Client client) {
        this.clients = client;
    }

    public void setPlans(Plans plans) {
        this.plans = plans;
    }
    public Client getClient() {
        return clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependents that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getTelephone(), that.getTelephone()) && Objects.equals(getKinship(), that.getKinship()) && Objects.equals(getCpf(), that.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTelephone(), getKinship(), getCpf());
    }

}