package com.physiotherapy.s.clinic.repository;

import com.physiotherapy.s.clinic.entities.Plans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepository extends JpaRepository<Plans, Long> {
}
