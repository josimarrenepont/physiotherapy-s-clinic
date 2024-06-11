package com.physiotherapy.s.clinic.plansTest;

import com.physiotherapy.s.clinic.entities.Client;
import com.physiotherapy.s.clinic.entities.Dependents;
import com.physiotherapy.s.clinic.entities.Plans;
import com.physiotherapy.s.clinic.repository.ClientRepository;
import com.physiotherapy.s.clinic.repository.DependentsRepository;
import com.physiotherapy.s.clinic.repository.PlansRepository;
import com.physiotherapy.s.clinic.service.PlansService;
import com.physiotherapy.s.clinic.service.exceptions.ResourceNotFoundExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PlansServiceTest {

    @Mock
    private PlansRepository plansRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private PlansService plansService;

    private Client client;
    private Plans plans;

    @BeforeEach
    public void setUp() {
        client = new Client();
        plans = new Plans(1L, null, 50.0, 100.0);
    }

    @Test
    public void getTotalPriceWithNoDependnets(){
        client.setDependents(Collections.emptySet());
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        when(plansRepository.findById(anyLong())).thenReturn(Optional.of(plans));

        Double totalPrice = plansService.getTotalPriceWithDependents(1L, 1L);

        assertEquals(100.0, totalPrice);
    }
    @Test
    public void getTotalPriceWithDependents() {
        Set<Dependents> dependents = new HashSet<>();

        dependents.add(new Dependents(1L, null, "1234567",
                "125698", "Casado"));
        dependents.add(new Dependents(2L, null, "1234567",
                "125698", "Casado"));

        client.setDependents(dependents);
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(client));
        when(plansRepository.findById(anyLong())).thenReturn(Optional.of(plans));

        Double totalPrice = plansService.getTotalPriceWithDependents(1L, 1L);

        assertEquals(200.0, totalPrice);
    }
}
