package com.example.app.services;

import com.example.app.models.agente.Agente;
import com.example.app.repositories.AgenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenteService {
    @Autowired
    private AgenteRepository agenteRepository;

        @Transactional
    public Agente create(Agente obj) {
        obj = this.agenteRepository.save(obj);
        return obj;
    }

    public List<Agente> findAll(){
        List<Agente> agenteList;
        return agenteList = agenteRepository.findAll();
    }
//    public Agente findByUserId(String id) {
//        Agente agente = this.agenteRepository.findByUserId(id);
//        return agente;
//    }

}
