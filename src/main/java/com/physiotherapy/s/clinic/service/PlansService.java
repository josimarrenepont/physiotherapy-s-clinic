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
    public Plans findById(Long id){
        Optional<Plans> obj = plansRepository.findById(id);
        return obj.orElseThrow();
    }

    public Plans update(Long id, Plans obj) {
        try {
            Plans entity = plansRepository.getReferenceById(id);
            updateData(entity, obj);
            return plansRepository.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    private void updateData(Plans entity, Plans obj) {
        entity.setPrice(obj.getPrice());
        entity.setAdditionalPricePerson(obj.getAdditionalPricePerson());
        entity.setNumberOfAdditionalPeople(obj.getNumberOfAdditionalPeople());
    }
}
