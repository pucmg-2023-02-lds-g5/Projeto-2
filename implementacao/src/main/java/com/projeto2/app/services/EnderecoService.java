package com.projeto2.app.services;

import com.projeto2.app.models.Cliente;
import com.projeto2.app.models.Endereco;
import com.projeto2.app.models.Usuario;
import com.projeto2.app.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private UserService userService;


    public Endereco findById(Long id){
        Optional<Endereco> endereco = this.enderecoRepository.findById(id);
        return endereco.orElseThrow(()->new RuntimeException("Tarefa não encontrada"));
    }
    @Transactional
    public Endereco create(Endereco obj){
        obj.setId(null);
         obj = this.enderecoRepository.save(obj);
        return obj;
    }
    @Transactional
    public Endereco update (Endereco obj){
        Endereco newObj = findById(obj.getId());
        newObj.setBairro(obj.getBairro());
        newObj.setBairro(obj.getCidade());
        newObj.setBairro(obj.getEstado());
        newObj.setBairro(obj.getRua());
        newObj.setBairro(obj.getNumero());
        return this.enderecoRepository.save(newObj);

    }

    public void delete(Long id){
        findById(id);
        try{
            this.enderecoRepository.deleteById(id);

        }catch (Exception e){
            throw new RuntimeException("Não foi possivel pois tem entidades relacionadas");
        }
    }
}
