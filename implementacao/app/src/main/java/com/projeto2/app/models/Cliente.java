package com.projeto2.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = Cliente.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente extends Usuario{
    public static final String TABLE_NAME = "cliente";


    @Column(name="registroGeral",length = 100,nullable = false,unique = true)
    @NotEmpty
    @NotNull
    private String registroGeral;



    @Column(name="nome",length = 100,nullable = false)
    @NotEmpty
    @NotNull
    private String nome;

    @Column(name="cpf",length = 100,nullable = false,unique = true)
    @NotEmpty
    @NotNull
    private String cpf;

    @Column(name="endereco",length = 255,nullable = false,unique = true)
    @NotEmpty
    @NotNull
    private String endereco;

    @OneToMany(mappedBy = "cliente")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Profissao> profissoes;


}
