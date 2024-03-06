package com.physiotherapy.s.clinic.controller;

import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.service.PlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/{id}")
    public ResponseEntity<Plans> findById(@PathVariable Long id){
        Plans obj = plansService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
