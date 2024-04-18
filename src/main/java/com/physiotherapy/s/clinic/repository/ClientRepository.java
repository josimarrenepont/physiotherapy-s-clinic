package com.physiotherapy.s.clinic.repository;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByName(String name);

    @Query(value= "SELECT obj FROM Dependents obj JOIN FETCH obj.clients")
    Set<Dependents> searchAll();
}
