package com.example.app.repositories;

import com.example.app.models.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente,String> {
//    @Query("SELECT c FROM Cliente c WHERE c.user_id = :id")
//    public Cliente findByUsuarioId(@Param("id") String id);
}
