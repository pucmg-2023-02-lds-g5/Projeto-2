package com.projeto2.app.services;

import com.projeto2.app.models.Agente;
import com.projeto2.app.models.Cliente;
import com.projeto2.app.repositories.AgenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgenteService {
    @Autowired
    private UserService userService;
    @Autowired
    private AgenteRepository agenteRepository;

    public Agente findById(Long id) {
        Optional<Agente> Agente = this.agenteRepository.findById(id);
        return Agente.orElseThrow(() -> new RuntimeException("Cliente não entrado"));
    }

    @Transactional
    public Agente create(Agente obj) {
        obj.setId(null);
        obj = this.agenteRepository.save(obj);
        return obj;
    }

    public List<Agente> findAll() {
        List<Agente> Agente = new ArrayList<>();
        Agente = this.agenteRepository.findAll();
        return Agente;
    }


    @Transactional
    public Agente update(Agente obj) {
        Agente newObj = findById(obj.getId());
        newObj.setSenha(obj.getSenha());
        return this.agenteRepository.save(newObj);
    }

    @Transactional
    public Agente updateName(String novoNome, Long id) {
        if (novoNome == null || novoNome.isEmpty()) {
            return null;
        }

        Agente newObj = findById(id);
        newObj.setNome(novoNome);
        return this.agenteRepository.save(newObj);
    }



    public void delete(Long id) {
        findById(id);
        try {
            this.agenteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas");
        }

    }
}
