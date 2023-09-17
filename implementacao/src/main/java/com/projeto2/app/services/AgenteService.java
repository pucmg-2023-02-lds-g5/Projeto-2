package com.projeto2.app.services;

import com.projeto2.app.models.Agente;
import com.projeto2.app.models.Cliente;
import com.projeto2.app.repositories.AgenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgenteService {
    @Autowired
    private UserService userService;
    @Autowired
    private AgenteRepository agenteRepository;

    @Transactional
    public Agente create(Agente obj) {
        obj.setId(null);
        obj = this.agenteRepository.save(obj);
        return obj;
    }
}
