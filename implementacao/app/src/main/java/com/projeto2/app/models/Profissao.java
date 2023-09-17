package com.projeto2.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome",length = 100,nullable = false,unique = true)
    @NotNull
    @NotEmpty
    @Size(min=2,max=100)
    private String nome;
    @Column(name="empregador",length = 100,nullable = false,unique = true)
    @NotNull
    @NotEmpty
    @Size(min=2,max=100)
    private String empregador;

    @Column(name="salario",length = 100,nullable = false,unique = true)
    @NotNull
    @NotEmpty
    private Double salario;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}
