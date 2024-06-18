package com.proyecto.alura.med.voll.medvollapi.domain.users.controllers.login;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.proyecto.alura.med.voll.medvollapi.domain.users.User;
import com.proyecto.alura.med.voll.medvollapi.infra.security.JWT_DTO;
import com.proyecto.alura.med.voll.medvollapi.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {    
    
    private AuthenticationManager authManager;
    private TokenService service;    

    public LoginController(AuthenticationManager authManager, TokenService service) {
        this.authManager = authManager;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<JWT_DTO> authUserEntity(@RequestBody @Valid User login){
        String username = login.getUser();
        String password = login.getPassword();
        Authentication authToken = new UsernamePasswordAuthenticationToken(username, password);
        var authUser = authManager.authenticate(authToken);
        String jwt_token = service.tokenGenerator((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JWT_DTO(jwt_token));
    }    
}


