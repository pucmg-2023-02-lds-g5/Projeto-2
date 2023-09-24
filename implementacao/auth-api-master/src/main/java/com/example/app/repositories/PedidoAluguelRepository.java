package com.example.app.repositories;

import com.example.app.models.pedidoAluguel.PedidoAluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel,Long> {
}
