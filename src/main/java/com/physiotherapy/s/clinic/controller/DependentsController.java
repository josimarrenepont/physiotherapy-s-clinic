package com.physiotherapy.s.clinic.controller;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.repository.ClientRepository;
import com.physiotherapy.s.clinic.repository.DependentsRepository;
import com.physiotherapy.s.clinic.service.DependentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/dependents")
public class DependentsController {
    @Autowired
    private DependentsService dependentsService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DependentsRepository dependentsRepository;

    @GetMapping
    public ResponseEntity<List<Dependents>> findAll(){
        List<Dependents> list = dependentsService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Dependents> findById(@PathVariable Long id){
        Dependents obj = dependentsService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping(value = "/{clientId}")
    public ResponseEntity<Dependents> insert(@PathVariable Long clientId, @RequestBody Dependents obj){
        Dependents dependent = dependentsService.insert(clientId, obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dependent.getId()).toUri();
        return ResponseEntity.created(uri).body(dependent);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        dependentsService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Dependents> update(@PathVariable Long id, @RequestBody Dependents obj){
        obj = dependentsService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
