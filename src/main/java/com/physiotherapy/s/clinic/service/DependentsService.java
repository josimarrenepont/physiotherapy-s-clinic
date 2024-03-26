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
import java.util.Set;

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


    public Dependents Insert(Long clientId, Long plansId, Long dependentsId, Dependents dependents) throws EntityNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        Optional<Plans> optionalPlans = plansRepository.findById(plansId);
        Optional<Dependents> optionalDependents = dependentsRepository.findById(dependentsId);
        if(optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Client not found. Id" + clientId);
        }
        if(optionalPlans.isEmpty()){
            throw new EntityNotFoundException("Plans not found. Id " + plansId);
        }

        Client client = optionalClient.get();
        Plans plans = optionalPlans.get();
        Dependents dependents1 = optionalDependents.get();
        dependents.setPlans(plans);
        dependents.setClient(client);
        dependents.setId(dependents.getId());

        client.setTotalNumberOfDependents(client.getTotalNumberOfDependents() + 1);
        client.setId(client.getId());

        plans.setId(plans.getId());
        plans.setPrice(plans.getPrice());
        plans.setAdditionalPricePerson(plans.getAdditionalPricePerson());

        dependents1.setId(dependents1.getId());
        dependents1.setClient(dependents1.getClient());
        dependents1.setPlans(plans);

        clientRepository.save(client);
        dependentsRepository.save(dependents);
        plansRepository.save(plans);

        return dependents;
    }

    public Dependents insert(Long clientId, Dependents obj) {
        Client client = clientRepository.findById(clientId).orElseThrow(
                () -> new ResourceNotFoundExceptions("Client not found with Id: " + clientId));
        obj.setClient(client);
        return dependentsRepository.save(obj);
    }
}
