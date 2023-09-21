package com.projeto2.app.services;

import com.projeto2.app.models.PedidoAluguel;
import com.projeto2.app.models.StatusAluguel;
import com.projeto2.app.repositories.PedidoAluguelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoAluguelService {
    @Autowired
    private PedidoAluguelRepository pedidoAluguelRepository;

    public PedidoAluguel findById(Long id){
        Optional<PedidoAluguel> pedidoAluguel = this.pedidoAluguelRepository.findById(id);
        return pedidoAluguel.orElseThrow(()-> new RuntimeException("Não foi encontrado o pedido"));
    }
    @Transactional
    public PedidoAluguel create(PedidoAluguel obj){
        obj.setId(null);
        obj = this.pedidoAluguelRepository.save(obj);
        return obj;
    }
    @Transactional
    public PedidoAluguel update(PedidoAluguel obj){
        PedidoAluguel newObj = findById(obj.getId());
        return this.pedidoAluguelRepository.save(newObj);
    }

    @Transactional
    public PedidoAluguel modificarStatus(StatusAluguel statusAluguel, Long id){
        PedidoAluguel newObj = findById(id);
        newObj.setStatus(statusAluguel);
        return this.pedidoAluguelRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try{
            this.pedidoAluguelRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel excluir o pedido pois há entidades relacionadas");

        }
    }
}
