package com.projeto2.app.services;

import com.projeto2.app.models.Agente;
import com.projeto2.app.models.Cliente;
import com.projeto2.app.models.Usuario;
import com.projeto2.app.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UserService userService;

    public Cliente findById(Long id) {
        Optional<Cliente> Cliente = this.clienteRepository.findById(id);
        return Cliente.orElseThrow(() -> new RuntimeException("Cliente não entrado"));
    }

    @Transactional
    public Cliente create(Cliente obj) {
        obj.setId(null);
        obj = this.clienteRepository.save(obj);
        return obj;
    }

    public List<Cliente> findAll() {
        List<Cliente> Cliente = new ArrayList<>();
        Cliente = this.clienteRepository.findAll();
        return Cliente;
    }


    @Transactional
    public Cliente update(Cliente obj) {
        Cliente newObj = findById(obj.getId());
        newObj.setSenha(obj.getSenha());
        return this.clienteRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas");
        }

    }

    @Transactional
    public Cliente updateName(String novoNome, String cpf, String rg, Long id) {
        Cliente newObj = findById(id);

        if (novoNome != null && !novoNome.isEmpty()) {
            newObj.setNome(novoNome);
        }

        if (cpf != null && !cpf.isEmpty()) {
            newObj.setCpf(cpf);
        }

        if (rg != null && !rg.isEmpty()) {
            newObj.setRegistroGeral(rg);
        }

        return this.clienteRepository.save(newObj);
    }
}
