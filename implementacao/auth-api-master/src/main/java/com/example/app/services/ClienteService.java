package com.example.app.services;

import com.example.app.models.cliente.Cliente;
import com.example.app.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente create(Cliente obj) {
        obj = this.clienteRepository.save(obj);
        return obj;
    }

    public List<Cliente> findAll() {
        List<Cliente> Cliente = new ArrayList<>();
        Cliente = this.clienteRepository.findAll();
        return Cliente;
    }

//    public Cliente findByUserId(String id) {
//        Cliente cliente = this.clienteRepository.findByUsuarioId(id);
//        return cliente;
//    }
}
