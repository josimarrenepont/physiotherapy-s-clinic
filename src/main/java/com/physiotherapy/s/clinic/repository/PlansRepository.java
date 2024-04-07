package com.physiotherapy.s.clinic.repository;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.entities.dto.ClientDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlansRepository extends JpaRepository<Plans, Long> {
}
