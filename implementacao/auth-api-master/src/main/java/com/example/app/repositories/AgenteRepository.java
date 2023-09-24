package com.example.app.repositories;

import com.example.app.models.agente.Agente;
import com.example.app.models.cliente.Cliente;
import com.example.app.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AgenteRepository extends JpaRepository<Agente, User> {
//    @Query("SELECT c FROM Agente a WHERE a.user_id = :id")
//    public Agente findByUserId(@Param("id") String id);
}
