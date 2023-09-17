package com.projeto2.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum TipoAgente {
    EMPRESA(1,"ROLE_EMPRESA"),
    BANCO(2,"ROLE_BANCO");

    private Integer code;
    private String description;

    public static TipoAgente toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (TipoAgente x : TipoAgente.values()) {
            if (code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }


}
