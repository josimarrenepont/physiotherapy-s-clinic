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
    private String kinship;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client clients;

    public Dependents(){}

    public Dependents(Long id, String name, String kinship) {
        this.id = id;
        this.name = name;
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

    public String getKinship() {
        return kinship;
    }

    public void setKinship(String kinship) {
        this.kinship = kinship;
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
}
