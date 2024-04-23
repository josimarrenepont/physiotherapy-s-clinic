package com.physiotherapy.s.clinic.service;

import com.physiotherapy.s.clinic.repository.UserRepository;
import com.physiotherapy.s.clinic.security.AccountCredentialsVO;
import com.physiotherapy.s.clinic.security.TokenVO;
import com.physiotherapy.s.clinic.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity signing(AccountCredentialsVO data){
        try{
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUsername(username);

            var tokenResponse = new TokenVO();
            if(user != null){
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            }else{
                throw new UsernameNotFoundException("username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        }catch (Exception e){
            throw new BadCredentialsException("Invalid username/password suppliers");
        }
    }
    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = userRepository.findByUsername(username);

        var tokenResponse = new TokenVO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }

}
