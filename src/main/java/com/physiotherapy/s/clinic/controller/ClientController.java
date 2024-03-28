package com.physiotherapy.s.clinic.controller;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.entities.dto.ClientDTO;
import com.physiotherapy.s.clinic.entities.dto.DependentsDTO;
import com.physiotherapy.s.clinic.repository.ClientRepository;
import com.physiotherapy.s.clinic.repository.DependentsRepository;
import com.physiotherapy.s.clinic.repository.PlansRepository;
import com.physiotherapy.s.clinic.service.ClientService;
import com.physiotherapy.s.clinic.service.PlansService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DependentsRepository dependentsRepository;
    @Autowired
    private PlansRepository plansRepository;

    @Autowired
    private PlansService plansService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<Client> list = clientService.findAll();
        List<ClientDTO> dtoList = list.stream().map(ClientDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Client obj = clientService.findById(id);
        ClientDTO objDTO = new ClientDTO(obj);
        return ResponseEntity.ok().body(objDTO);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto){
        Client obj = clientService.update(id, dto);
        ClientDTO objDTO = new ClientDTO(obj);
        return ResponseEntity.ok().body(objDTO);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(value = "/{plansId}")
    public ResponseEntity<ClientDTO> insert(@PathVariable Long plansId, @RequestBody ClientDTO dto){
        Client client = clientService.insert(plansId, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClientDTO(client));
    }

}