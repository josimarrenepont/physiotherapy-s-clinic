package com.physiotherapy.s.clinic.controller;

import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.entities.dto.DependentsDTO;
import com.physiotherapy.s.clinic.repository.ClientRepository;
import com.physiotherapy.s.clinic.repository.DependentsRepository;
import com.physiotherapy.s.clinic.service.DependentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<DependentsDTO>> findAll(){
        List<Dependents> list = dependentsService.findAll();
        List<DependentsDTO> dtoList = list.stream().map(DependentsDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<DependentsDTO> findById(@PathVariable Long id){
        Dependents obj = dependentsService.findById(id);
        DependentsDTO objDto = new DependentsDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }
    @PostMapping(value = "/{clientId}")
    public ResponseEntity<DependentsDTO> insert(@PathVariable Long clientId, @RequestBody DependentsDTO dto){
        Dependents dependent = dependentsService.insert(clientId, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dependent.getId()).toUri();
        return ResponseEntity.created(uri).body(new DependentsDTO(dependent));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        dependentsService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<DependentsDTO> update(@PathVariable Long id, @RequestBody DependentsDTO dto){
        Dependents obj = dependentsService.update(id, dto);
        DependentsDTO dependentsDTO = new DependentsDTO(obj);
        return ResponseEntity.ok().body(dependentsDTO);
    }
}
