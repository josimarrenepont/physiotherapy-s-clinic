package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow();
    }

    public Client insert(Client obj) {
        return clientRepository.save(obj);
    }

    public Client update(Long id, Client obj) {
        Client entity = clientRepository.getReferenceById(id);
        updateData(entity, obj);
        return clientRepository.save(entity);
    }

    private void updateData(Client entity, Client obj) {
        entity.setEmail(obj.getEmail());
        entity.setMaritalStatus(obj.getMaritalStatus());
        entity.setTelephone(obj.getTelephone());
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}