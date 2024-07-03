package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.entities.dto.ClientDTO;
import com.physiotherapy.s.clinic.repository.ClientRepository;
import com.physiotherapy.s.clinic.repository.PlansRepository;
import com.physiotherapy.s.clinic.service.exceptions.ResourceNotFoundExceptions;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PlansService {
    @Autowired
    private PlansRepository plansRepository;
    @Autowired
    private ClientRepository clientRepository;

    public List<Plans> findAll() {
        return plansRepository.findAll();
    }

    public Plans findById(Long id) {
        Optional<Plans> obj = plansRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundExceptions(id));
    }

    public Plans update(Long id, Plans obj) {
        try {
            Plans entity = plansRepository.getReferenceById(id);
            updateData(entity, obj);
            return plansRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExceptions(id);
        }
    }

    private void updateData(Plans entity, Plans obj) {
        if (obj.getAdditionalPricePerson() != null) {
            entity.setAdditionalPricePerson(obj.getAdditionalPricePerson());
        }
        if (obj.getMoment() != null) {
            entity.setMoment(obj.getMoment());
        } else {
            entity.setMoment(LocalDate.now());
        }

        entity.setPrice(obj.getPrice());
    }
    public Plans insert(Plans obj) {
        return plansRepository.save(obj);
    }

    public Double getTotalPriceWithDependents(Long plansId, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Client not found with id: " + clientId));

        Plans plans = plansRepository.findById(plansId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Plans not found with id: " + plansId));

        return plans.getTotalPriceWithDependents(client);
    }

    public Plans getRandomPlan() {
        List<Plans> allPlans = plansRepository.findAll();
        if (allPlans.isEmpty()) {
            throw new ResourceNotFoundExceptions("No plans available");
        }
        int randomIndex = new Random().nextInt(allPlans.size());
        return allPlans.get(randomIndex);
    }

    public List<Plans> findByClientsId(Long clientsId) {
     return plansRepository.findByClientsId(clientsId);
    }
}