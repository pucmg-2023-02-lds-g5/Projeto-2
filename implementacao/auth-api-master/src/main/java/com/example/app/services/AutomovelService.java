package com.example.app.services;

import com.example.app.models.automovel.Automovel;
import com.example.app.repositories.AutomovelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutomovelService {
    @Autowired
    private AutomovelRepository automovelRepository;

    public Automovel findById(String id){
        Optional<Automovel> automovel = this.automovelRepository.findById(id);
        return automovel.orElseThrow(()->new RuntimeException("Automovel não encontrado"));
    }

    @Transactional
    public Automovel create(Automovel obj){
        obj.setMatricula(null);
        obj = this.automovelRepository.save(obj);
        return obj;
    }

    public List<Automovel> findAll(){
        List<Automovel> automovelList = this.automovelRepository.findAll();
        return automovelList;
    }

    public Automovel findByPlaca(String placa){
        return this.automovelRepository.findByPlaca(placa);
    }

    @Transactional
    public Automovel update(Automovel obj){
        Automovel newObj = findById(obj.getMatricula());
        return this.automovelRepository.save(newObj);
    }

    public void delete(String id){
        findById(id);
        try {
            this.automovelRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não foi possivel excluir o automovel");
        }

    }
}
