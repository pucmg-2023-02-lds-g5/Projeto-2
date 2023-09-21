package com.projeto2.app.repositories;

import com.projeto2.app.models.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AutomovelRepository extends JpaRepository<Automovel,Long> {
    @Query("SELECT a FROM Automovel a WHERE a.placa =:placa ")
    Automovel findByPlaca(@Param("placa") String placa);
}
