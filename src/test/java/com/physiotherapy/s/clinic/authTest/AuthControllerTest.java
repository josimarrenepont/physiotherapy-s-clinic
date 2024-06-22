// AuthControllerTest.java
package com.physiotherapy.s.clinic.authTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.physiotherapy.s.clinic.security.AccountCredentialsVO;
import com.physiotherapy.s.clinic.service.AuthService;
import com.physiotherapy.s.clinic.service.exceptions.InvalidJwtAuthenticationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    private AccountCredentialsVO accountCredentialsVO;
    private String validToken;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        accountCredentialsVO = new AccountCredentialsVO("user", "password");
        validToken = "Bearer valid.jwt.token";

        Mockito.when(authService.signing(any(AccountCredentialsVO.class))).thenReturn(ResponseEntity.ok(validToken));
        Mockito.when(authService.refreshToken(any(String.class), any(String.class))).thenReturn(ResponseEntity.ok(validToken));

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser
    public void testSigning_withValidCredentials_shouldReturnToken() throws Exception {
        mockMvc.perform(post("/auth/signing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountCredentialsVO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testSigning_withInvalidCredentials_shouldReturnForbidden() throws Exception {
        AccountCredentialsVO invalidCredentials = new AccountCredentialsVO("invalidUser", "invalidPassword");

        Mockito.when(authService.signing(any(AccountCredentialsVO.class)))
                .thenThrow(new InvalidJwtAuthenticationException("Invalid credentials"));

        mockMvc.perform(post("/auth/signing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidCredentials)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testRefreshToken_withValidToken_shouldReturnNewToken() throws Exception {
        mockMvc.perform(put("/auth/refresh/user")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer valid.refresh.token"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRefreshToken_withInvalidToken_shouldReturnForbidden() throws Exception {
        String invalidToken = "Bearer invalidToken";
        String username = "invalidUser";

        Mockito.when(authService.refreshToken(anyString(), anyString()))
                .thenThrow(new InvalidJwtAuthenticationException("Invalid Credentials"));

        mockMvc.perform(put("/auth/refresh/user")
                        .header("Authorization", invalidToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
