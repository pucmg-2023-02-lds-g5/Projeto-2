package com.projeto2.app.services;

import com.projeto2.app.models.Cliente;
import com.projeto2.app.models.Usuario;
import com.projeto2.app.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public Cliente create(Cliente obj) {
        obj.setId(null);
        obj = this.clienteRepository.save(obj);
        return obj;
    }
}
