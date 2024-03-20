package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.repository.ClientRepository;
import com.physiotherapy.s.clinic.repository.DependentsRepository;
import com.physiotherapy.s.clinic.repository.PlansRepository;
import com.physiotherapy.s.clinic.service.exceptions.DatabaseExceptions;
import com.physiotherapy.s.clinic.service.exceptions.ResourceNotFoundExceptions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DependentsService {

    @Autowired
    private DependentsRepository dependentsRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PlansRepository plansRepository;

    public List<Dependents> findAll(){
        return dependentsRepository.findAll();
    }
    public Dependents findById(Long id){
        Optional<Dependents> obj = dependentsRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundExceptions(id));
    }
    public Dependents insert(Dependents obj){
        return dependentsRepository.save(obj);
    }
    public void delete(Long id){
        try {
            dependentsRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundExceptions(id);
        }
         catch (DataIntegrityViolationException e){
            throw new DatabaseExceptions(e.getMessage());
        }
    }
    public Dependents update(Long id, Dependents obj){
        try {
            Dependents entity = dependentsRepository.getReferenceById(id);
            updateData(entity, obj);
            return dependentsRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundExceptions(id);
        }
    }

    private void updateData(Dependents entity, Dependents obj) {
        entity.setTelephone(obj.getTelephone());
    }

    public void createDependent(Long clientId, Dependents dependents) throws EntityNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if(!optionalClient.isPresent()) {
            throw new EntityNotFoundException("Client not found. Id" + clientId);
        }
            Client client = optionalClient.get();
            dependents.setClient(client);
            client.setTotalNumberOfDependents(client.getTotalNumberOfDependents() + 1);
            clientRepository.save(client);
            dependentsRepository.save(dependents);

        }

    public Dependents insert(Long clientId, Dependents obj) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundExceptions("Client not found with Id: " + clientId));
        obj.setClient(client);
        return dependentsRepository.save(obj);
    }
    public Dependents insert(Long clientId, Long plansId, Dependents obj){
        Plans plans = plansRepository.findById(plansId).
                orElseThrow(()-> new ResourceNotFoundExceptions("Plans not found with Id " + plansId));
        obj.setPlans(plans);
        return dependentsRepository.save(obj);
    }
}
