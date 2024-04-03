package com.physiotherapy.s.clinic.repository;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.entities.dto.ClientDTO;
import com.physiotherapy.s.clinic.entities.dto.DependentsDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependentsRepository extends JpaRepository<Dependents, Long> {
    Client save(DependentsDTO client);

    Dependents save(Dependents dependents);

    ClientDTO save(ClientDTO objDto);
}
