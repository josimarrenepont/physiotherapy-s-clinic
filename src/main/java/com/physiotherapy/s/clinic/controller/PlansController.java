package com.physiotherapy.s.clinic.controller;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.repository.ClientRepository;
import com.physiotherapy.s.clinic.service.ClientService;
import com.physiotherapy.s.clinic.service.PlansService;
import com.physiotherapy.s.clinic.service.exceptions.ResourceNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/plans")
public class PlansController {

    @Autowired
    private PlansService plansService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<Plans>> findAll(){
        List<Plans> list = plansService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id){
        Plans obj = plansService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Plans> update(@PathVariable Long id, @RequestBody Plans obj){
        obj = plansService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping
    public ResponseEntity<Plans> insert(@RequestBody Plans obj){
        obj = plansService.insert(obj);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping(value = "/{id}/totalPrice")
    public ResponseEntity<Double> getTotalPriceWithDependents(@PathVariable Long id, @RequestParam Long clientId) {
        Double totalPrice = plansService.getTotalPriceWithDependents(id, clientId);
        return ResponseEntity.ok().body(totalPrice);
    }
}
