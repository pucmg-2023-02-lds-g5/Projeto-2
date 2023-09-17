package com.projeto2.app.repositories;

import com.projeto2.app.models.Profissao;
import com.projeto2.app.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao,Long> {
}
