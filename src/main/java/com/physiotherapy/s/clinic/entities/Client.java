package com.physiotherapy.s.clinic.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer cpf;
    private Integer rg;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    private Character sex;
    private String maritalStatus;
    private String email;
    private String telephone;
    private String profession;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "plans_client", joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "plans_id"))
    private Set<Plans> plans = new HashSet<>();

    @ManyToMany(mappedBy = "clients")
    private Set<Dependents> dependents = new HashSet<>();
    public Client(){}

    public Client(Long id, String name, Integer cpf, Integer rg, LocalDate dateOfBirth,
                  Character sex, String maritalStatus, String email, String telephone,
                  String profession) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.maritalStatus = maritalStatus;
        this.email = email;
        this.telephone = telephone;
        this.profession = profession;
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

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public Integer getRg() {
        return rg;
    }

    public void setRg(Integer rg) {
        this.rg = rg;
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

    public Set<Plans> getPlans() {
        return plans;
    }

    public Integer getTotalNumberOfDependents(){
        return this.dependents.size();
    }

    public void setTotalNumberOfDependents(int totalNumberOfDependents) {
        setTotalNumberOfDependents(getTotalNumberOfDependents());
    }

    public void setDependents(Set<Dependents> dependents) {
        this.dependents = dependents;
    }
    public Collection<Object> getDependents() {
        return Arrays.asList(dependents.toArray());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(getId(), client.getId()) && Objects.equals(getName(), client.getName())
                && Objects.equals(getCpf(), client.getCpf())
                && Objects.equals(getRg(), client.getRg()) && Objects.equals(getDateOfBirth(), client.getDateOfBirth())
                && Objects.equals(getSex(), client.getSex()) && Objects.equals(getMaritalStatus(), client.getMaritalStatus())
                && Objects.equals(getEmail(), client.getEmail()) && Objects.equals(getTelephone(), client.getTelephone())
                && Objects.equals(getProfession(), client.getProfession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCpf(), getRg(), getDateOfBirth(),
                getSex(), getMaritalStatus(), getEmail(), getTelephone(), getProfession());
    }

    public void setPlans(Plans plans) {
        this.setId(getId());
    }
}