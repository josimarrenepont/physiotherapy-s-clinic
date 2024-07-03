package com.physiotherapy.s.clinic.repository;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.entities.dto.ClientDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlansRepository extends JpaRepository<Plans, Long> {
    @Query("SELECT p FROM Plans p JOIN p.clients c WHERE c.id = :clientsId")
    List<Plans> findByClientsId(@Param("clientsId") Long clientsId);
}
