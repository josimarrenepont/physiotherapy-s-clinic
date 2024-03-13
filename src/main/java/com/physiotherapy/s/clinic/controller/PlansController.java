package com.physiotherapy.s.clinic.controller;

import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.service.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/plans")
public class PlansController {

    @Autowired
    private PlansService plansService;

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
}
