package com.projeto2.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name= Endereco.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {
    public static final String TABLE_NAME = "endereco";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="estado",length = 100,nullable = false,unique = true)
    @NotNull
    @NotEmpty
    @Size(min=2,max=100)
    private String estado;
    @Column(name="cidade",length = 100,nullable = false,unique = true)
    @NotNull
    @NotEmpty
    @Size(min=2,max=100)
    private String cidade;
    @Column(name="bairro",length = 100,nullable = false,unique = true)
    @NotNull
    @NotEmpty
    @Size(min=2,max=100)
    private String bairro;
    @Column(name="rua",length = 100,nullable = false,unique = true)
    @NotNull
    @NotEmpty
    @Size(min=2,max=100)
    private String rua;
    @Column(name="numero",length = 100,nullable = false,unique = true)
    @NotNull
    @NotEmpty
    @Size(min=2,max=100)
    private String numero;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
