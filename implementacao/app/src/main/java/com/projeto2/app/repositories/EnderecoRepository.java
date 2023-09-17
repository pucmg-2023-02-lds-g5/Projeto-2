package com.projeto2.app.repositories;

import com.projeto2.app.models.Endereco;
import com.projeto2.app.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
