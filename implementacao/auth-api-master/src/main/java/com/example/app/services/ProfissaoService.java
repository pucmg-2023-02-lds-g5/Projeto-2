package com.example.app.services;

import com.example.app.models.profissao.Profissao;
import com.example.app.models.user.User;
import com.example.app.repositories.ProfissaoRepository;
import com.example.app.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissaoService {
    @Autowired
    private ProfissaoRepository profissaoRepository;
    @Autowired
    private UserRepository userRepository;

    public Profissao findById(Long id){
        Optional<Profissao> profissao = this.profissaoRepository.findById(id);
        return profissao.orElseThrow(()->new RuntimeException("não foi encontrada a profissao"));
    }
//    public List<Profissao> findProfissoesByUserId(String userId) {
//        List<Profissao> profissao = profissaoRepository.findProfissoesByUserId(userId);
//        return profissao;
//    }
    @Transactional
    public Profissao create(Profissao obj){
        return this.profissaoRepository.save(obj);
    }
    public void delete(Long id){
        try {
            this.profissaoRepository.deleteById(id);

        }catch (Exception e){
            throw new RuntimeException("Não é possivel excluir profissoes pois há entidades relacionadas");
        }
    }

    @Transactional
    public Profissao update(Profissao obj){
        Profissao newObj = this.findById(obj.getId());
        return this.profissaoRepository.save(newObj);
    }

    public List<Profissao> findAll(){
        return this.profissaoRepository.findAll();
    }

}
