package com.physiotherapy.s.clinic.plansTest;

import com.physiotherapy.s.clinic.controller.PlansController;
import com.physiotherapy.s.clinic.service.PlansService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PlansControllerTest {
    @InjectMocks
    private PlansController plansController;

    @Mock
    private PlansService plansService;

    @Mock
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(plansController).build();
    }
    @Test
    public void testGetTotalPriceWithDependents() throws Exception{
        when(plansService.getTotalPriceWithDependents(anyLong(), anyLong())).thenReturn( 100.0);

        ResultActions result = mockMvc.perform(get("/plans/1/totalPrice")
                        .param("clientId", "1")
                        .contentType(MediaType.APPLICATION_JSON));

               result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("100.0"));
    }
}
