package com.physiotherapy.s.clinic.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;

import java.time.LocalDate;
import java.util.List;

public class ClientDTO {
    private Long id;
    private String name;
    private String cpf;
    private String rg;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private Character sex;
    private String maritalStatus;
    private String email;
    private String telephone;
    private String profession;
    private List<DependentsDTO> dependentsDTOS;

    public ClientDTO(){}

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.rg = client.getRg();
        this.dateOfBirth = client.getDateOfBirth();
        this.sex = client.getSex();
        this.maritalStatus = client.getMaritalStatus();
        this.email = client.getEmail();
        this.telephone = client.getTelephone();
        this.profession = client.getProfession();
    }

    public List<DependentsDTO> getDependents() {
        return dependentsDTOS;
    }
    public void setDependents(List<DependentsDTO> dependentsDTOS){
        this.dependentsDTOS = dependentsDTOS;
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

    public String getRg() {
        return rg;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Character getSex() {
        return sex;
    }

    public void setSex(Character sex) {
        this.sex = sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
