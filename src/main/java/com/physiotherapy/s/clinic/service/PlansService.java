package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.repository.PlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PlansService {
    @Autowired
    private PlansRepository plansRepository;

    public List<Plans> findAll(){
        return plansRepository.findAll();
    }
    public Plans findById(@PathVariable Long id){
        Optional<Plans> obj = plansRepository.findById(id);
        return obj.orElseThrow();
    }
}
