package com.projeto2.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = Automovel.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Automovel {
    public static final String TABLE_NAME = "automovel";

    @Column(name="matricula",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long matricula;

    @Column(name="ano")
    @NotNull
    @NotEmpty
    private String ano;
    @Column(name="marca")
    @NotNull
    @NotEmpty
    private String marca;
    @Column(name="modelo")
    @NotNull
    @NotEmpty
    private String modelo;
    @Column(name="placa",unique = true)
    @NotNull
    @NotEmpty
    private String placa;
    @Column(name="diaria")
    @NotNull
    private Double diaria;
    @Column(name="disponivel",columnDefinition = "boolean")
    @NotNull
    private boolean disponivel;
}
