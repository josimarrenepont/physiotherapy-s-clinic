package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.repository.PlansRepository;
import com.physiotherapy.s.clinic.service.exceptions.ResourceNotFoundExceptions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public Plans findById(Long id){
        Optional<Plans> obj = plansRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundExceptions(id));
    }

    public Plans update(Long id, Plans obj) {
        try {
            Plans entity = plansRepository.getReferenceById(id);
            updateData(entity, obj);
            return plansRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExceptions(id);
        }
    }

    private void updateData(Plans entity, Plans obj) {
        if(obj.getAdditionalPricePerson() != null){
            entity.setAdditionalPricePerson(obj.getAdditionalPricePerson());
        }
        if(obj.getMoment() != null){
            entity.setMoment(obj.getMoment());
        }

        entity.setPrice(obj.getPrice());

    }

    public Plans insert(Plans obj) {
        return plansRepository.save(obj);
    }
}
