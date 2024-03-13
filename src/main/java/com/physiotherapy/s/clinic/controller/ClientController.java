package com.physiotherapy.s.clinic.controller;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.dto.ClientDTO;
import com.physiotherapy.s.clinic.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;
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
    @PostMapping
    public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto){
        Client obj = clientService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClientDTO(obj));
    }
}
