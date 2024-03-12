package com.physiotherapy.s.clinic.controller;

import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.service.DependentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/dependents")
public class DependentsController {
    @Autowired
    private DependentsService dependentsService;
    @GetMapping
    public ResponseEntity<List<Dependents>> findAll(){
        List<Dependents> list = dependentsService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Dependents> findById(@PathVariable Long id){
        Dependents obj = dependentsService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping
    public ResponseEntity<Dependents> insert(@RequestBody Dependents obj){
        obj = dependentsService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        dependentsService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Dependents> update(@PathVariable Long id, @RequestBody Dependents obj){
        obj = dependentsService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
