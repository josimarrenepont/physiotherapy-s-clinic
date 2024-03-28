package com.physiotherapy.s.clinic.entities.dto;

import com.physiotherapy.s.clinic.entities.Dependents;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DependentsDTO {

    private Long id;
    private String name;
    private String telephone;
    private String kinship;
    private String cpf;

    public DependentsDTO(){}

    public DependentsDTO(Dependents dependents){
        this.id = dependents.getId();
        this.name = dependents.getName();
        this.cpf = dependents.getCpf();
        this.telephone = dependents.getTelephone();
        this.kinship = dependents.getKinship();

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
