package com.projeto2.app.services;

import com.projeto2.app.models.Profissao;
import com.projeto2.app.repositories.ProfissaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfissaoService {
    @Autowired
    private ProfissaoRepository profissaoRepository;

    public Profissao findById(Long id){
        Optional<Profissao> profissao = this.profissaoRepository.findById(id);
        return profissao.orElseThrow(()->new RuntimeException("não foi encontrada a profissao"));
    }
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
}
