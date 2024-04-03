package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.entities.dto.ClientDTO;
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
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DependentsRepository dependentsRepository;
    @Autowired
    private PlansRepository plansRepository;
    private Client client;
    private Long plansId;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundExceptions(id));
    }

    public Client insert(ClientDTO obj) {

        Client client = new Client();
        client.setName(obj.getName());
        client.setRg(obj.getRg());
        client.setCpf(obj.getCpf());
        client.setTelephone(obj.getTelephone());
        client.setEmail(obj.getEmail());
        client.setMaritalStatus(obj.getMaritalStatus());
        client.setProfession(obj.getProfession());
        client.setDateOfBirth(obj.getDateOfBirth());
        client.setSex(obj.getSex());
        client.getDependents().size();
        return clientRepository.save(client);
    }
    public void delete(Long id) {
        try{
            clientRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundExceptions(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseExceptions(e.getMessage());
        }
    }
    public Client update(Long id, ClientDTO obj) {
        try {
            Client entity = clientRepository.getReferenceById(id);
            updateData(entity, obj);
            return clientRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundExceptions(id);
        }
    }

    private void updateData(Client entity, ClientDTO obj) {
        entity.setEmail(obj.getEmail());
        entity.setMaritalStatus(obj.getMaritalStatus());
        entity.setTelephone(obj.getTelephone());
        entity.setProfession(obj.getProfession());
    }
    public void associateDependent(Long dependentsId, Long clientId){
        Optional<Dependents> optionalDependents = dependentsRepository.findById(dependentsId);
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if(!optionalDependents.isPresent()){
            throw new EntityNotFoundException("Dependents not found, Id! " + dependentsId);
        }
        if(!optionalClient.isPresent()){
            throw new EntityNotFoundException("Client not found, Id! " + clientId);
        }

        Dependents dependents = optionalDependents.get();
        Client client = optionalClient.get();
        dependents.setClient(client);
        client.setTotalNumberOfDependents(client.getTotalNumberOfDependents() + 1);

        dependentsRepository.save(dependents);
        clientRepository.save(client);
    }
}


