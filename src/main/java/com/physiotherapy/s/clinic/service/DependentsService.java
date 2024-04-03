package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.entities.dto.DependentsDTO;
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
    public Dependents update(Long id, DependentsDTO obj){
        try {
            Dependents entity = dependentsRepository.getReferenceById(id);
            updateData(entity, obj);
            return dependentsRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundExceptions(id);
        }
    }

    private void updateData(Dependents entity, DependentsDTO obj) {
        entity.setTelephone(obj.getTelephone());
    }


    public Dependents insert(Long clientId, DependentsDTO obj) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if(optionalClient.isEmpty()) {
            throw new EntityNotFoundException("Client not found. Id" + clientId);
        }

        Client client = optionalClient.get();
        Dependents dependents = new Dependents();
        dependents.setClient(client);
        dependents.setName(obj.getName());
        dependents.setId(obj.getId());
        dependents.setCpf(obj.getCpf());
        dependents.setKinship(obj.getKinship());
        dependents.setTelephone(obj.getTelephone());
        client.setId(client.getId());
        dependents.setId(client.getId());
        clientRepository.save(client);

        return dependentsRepository.save(dependents);
    }
}
