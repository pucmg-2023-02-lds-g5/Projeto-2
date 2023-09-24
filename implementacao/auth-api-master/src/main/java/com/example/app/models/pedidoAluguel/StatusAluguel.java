package com.example.app.models.pedidoAluguel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusAluguel {
    Solicitado(1,"SOLICITADO"),
    Contratado(2,"CONTRATADO"),
    Cancelado(3,"CANCELADO");


    private Integer code;
    private String description;
    public static StatusAluguel fromDescription(String description) {
        description = description.toUpperCase();
        for (StatusAluguel status : StatusAluguel.values()) {
            if (status.getDescription().equals(description)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + description);
    }
}
