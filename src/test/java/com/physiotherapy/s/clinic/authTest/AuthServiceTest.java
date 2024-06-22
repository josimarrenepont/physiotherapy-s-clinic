// AuthServiceTest.java
package com.physiotherapy.s.clinic.authTest;

import com.physiotherapy.s.clinic.entities.User;
import com.physiotherapy.s.clinic.repository.UserRepository;
import com.physiotherapy.s.clinic.security.AccountCredentialsVO;
import com.physiotherapy.s.clinic.security.TokenVO;
import com.physiotherapy.s.clinic.security.jwt.JwtTokenProvider;
import com.physiotherapy.s.clinic.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthServiceTest {

    @Mock
    private JwtTokenProvider tokenProvider;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private AccountCredentialsVO validCredentials;
    private AccountCredentialsVO invalidCredentials;
    private String username;
    private String password;
    private String refreshToken;

    @BeforeEach
    public void setUp() {
        username = "user";
        password = "password";
        validCredentials = new AccountCredentialsVO(username, password);
        invalidCredentials = new AccountCredentialsVO("invalidUser", "invalidPassword");
        refreshToken = "valid.refresh.token";
    }

    @Test
    public void testSigning_withValidCredentials_shouldReturnToken() {
        User mockUser = new User(1L, username, password, true, true, true, true, Collections.emptyList());

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userRepository.findByUsername(username)).thenReturn(mockUser);
        when(tokenProvider.createAccessToken(username, mockUser.getRoles())).thenReturn(new TokenVO());

        ResponseEntity<?> response = authService.signing(validCredentials);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void testSigning_withInvalidCredentials_shouldThrowBadCredentialsException() {
        doThrow(new BadCredentialsException("invalid username/password supplied"))
                .when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThrows(BadCredentialsException.class, () -> authService.signing(invalidCredentials));
    }

    @Test
    public void testSigning_withUnknownUsername_shouldThrowUsernameNotFoundException() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> authService.signing(validCredentials));
    }

    @Test
    public void testRefreshToken_withValidUsernameAndToken_shouldReturnNewToken() {
        User mockUser = new User(1L, username, password, true, true, true, true, Collections.emptyList());

        when(userRepository.findByUsername(username)).thenReturn(mockUser);
        when(tokenProvider.refreshToken(refreshToken)).thenReturn(new TokenVO());

        ResponseEntity<?> response = authService.refreshToken(username, refreshToken);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void testRefreshToken_withUnknownUsername_shouldThrowUsernameNotFoundException() {
        when(userRepository.findByUsername(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> authService.refreshToken(username, refreshToken));
    }
}
