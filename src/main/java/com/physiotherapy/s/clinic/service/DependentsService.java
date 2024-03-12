package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.repository.DependentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependentsService {

    @Autowired
    private DependentsRepository dependentsRepository;

    public List<Dependents> findAll(){
        return dependentsRepository.findAll();
    }
    public Dependents findById(Long id){
        Optional<Dependents> obj = dependentsRepository.findById(id);
        return obj.orElseThrow();
    }
    public Dependents insert(Dependents obj){
        return dependentsRepository.save(obj);
    }
    public void delete(Long id){
        dependentsRepository.deleteById(id);
    }
    public Dependents update(Long id, Dependents obj){
        Dependents entity = dependentsRepository.getReferenceById(id);
        updateData(entity, obj);
        return dependentsRepository.save(entity);
    }

    private void updateData(Dependents entity, Dependents obj) {
        entity.setName(obj.getName());
    }
}
