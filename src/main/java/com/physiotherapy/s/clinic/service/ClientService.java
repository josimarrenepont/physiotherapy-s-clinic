package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.dto.ClientDTO;
import com.physiotherapy.s.clinic.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    return clientRepository.save(client);
    }
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
    public Client update(Long id, ClientDTO obj) {
        Client entity = clientRepository.getReferenceById(id);
        updateData(entity, obj);
        return clientRepository.save(entity);
    }

    private void updateData(Client entity, ClientDTO obj) {
        entity.setEmail(obj.getEmail());
        entity.setMaritalStatus(obj.getMaritalStatus());
        entity.setTelephone(obj.getTelephone());
    }

}