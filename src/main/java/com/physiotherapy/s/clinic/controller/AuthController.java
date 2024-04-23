package com.physiotherapy.s.clinic.controller;

import com.physiotherapy.s.clinic.security.AccountCredentialsVO;
import com.physiotherapy.s.clinic.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.apache.logging.log4j.util.Strings.isBlank;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signing")
    public ResponseEntity signing(@RequestBody AccountCredentialsVO data){
             if(checkIfParamsIsNotNull(data)) {
                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
             }
             var token = authService.signing(data);
             if(token == null) {
                 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Username or password is incorrect or User not found");
             }else{
                 return token;
             }
    }
    private Boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
        return data == null || isBlank(data.getUsername()) || isBlank(data.getPassword());
    }
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authService.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

}
