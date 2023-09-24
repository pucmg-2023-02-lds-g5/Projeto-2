package com.example.app.repositories;

import com.example.app.models.automovel.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutomovelRepository extends JpaRepository<Automovel,String> {
    @Query("SELECT a FROM Automovel a WHERE a.placa =:placa ")
    Automovel findByPlaca(@Param("placa") String placa);
}
