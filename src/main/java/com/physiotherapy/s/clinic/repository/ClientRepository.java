package com.physiotherapy.s.clinic.repository;

import com.physiotherapy.s.clinic.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByName(String name);
}
