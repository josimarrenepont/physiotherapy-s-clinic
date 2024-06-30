package com.physiotherapy.s.clinic.repository;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.entities.dto.ClientDTO;
import com.physiotherapy.s.clinic.entities.dto.DependentsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface DependentsRepository extends JpaRepository<Dependents, Long> {
    Client save(DependentsDTO client);

    Dependents save(Dependents dependents);

    ClientDTO save(ClientDTO objDto);

    @Query("SELECT obj FROM Client obj JOIN FETCH obj.dependents")
    Set<Client> searchAll();

    List<Dependents> findByClientsId(Long clientsId);
}
