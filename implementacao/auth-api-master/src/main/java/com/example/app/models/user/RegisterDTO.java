package com.example.app.models.user;

import com.example.app.models.agente.TipoAgente;

public record RegisterDTO(String login, String password, UserRole role, String cpf, String endereco, String nome, String registroGeral,
                          TipoAgente agente) {
}
