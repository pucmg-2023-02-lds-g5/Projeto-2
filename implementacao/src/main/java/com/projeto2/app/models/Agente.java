package com.projeto2.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Agente.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Agente extends Usuario{

    public static final String TABLE_NAME = "agente";


    @Column(name="nome",length = 100,nullable = false)
    @NotEmpty
    @NotNull
    private String nome;
    @Column(name="tipoAgente",length = 60,nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAgente tipoAgente;

}
