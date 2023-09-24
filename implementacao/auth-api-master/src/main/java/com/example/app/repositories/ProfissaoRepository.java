package com.example.app.repositories;

import com.example.app.models.cliente.Cliente;
import com.example.app.models.profissao.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProfissaoRepository extends JpaRepository<Profissao,Long> {
//    @Query("SELECT p FROM Profissao p WHERE p.cliente = :cliente")
//    List<Profissao> findProfissoesByUserId(@Param("cliente") Cliente cliente);


}
