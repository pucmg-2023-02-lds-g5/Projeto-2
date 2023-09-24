package com.example.app.controllers;

import com.example.app.models.agente.Agente;
import com.example.app.models.cliente.Cliente;
import com.example.app.models.user.*;
import com.example.app.infra.security.TokenService;
import com.example.app.repositories.AgenteRepository;
import com.example.app.repositories.ClienteRepository;
import com.example.app.repositories.UserRepository;
import com.example.app.services.AgenteService;
import com.example.app.services.ClienteService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AgenteService agenteService;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data, HttpServletResponse response){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        response.addCookie(new Cookie("authtoken", token));
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();


        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role(),data.nome());

        this.repository.save(newUser);

        if(newUser.getRole() == UserRole.AGENTE){
            Agente obj = new Agente(data.agente(),newUser.getId());
            agenteService.create(obj);
            
        } else if (newUser.getRole() == UserRole.USER ) {
            Cliente obj = new Cliente(data.cpf(),data.endereco(),data.registroGeral(),newUser.getId());
            clienteService.create(obj);
        }

        return ResponseEntity.ok().build();
    }
}
