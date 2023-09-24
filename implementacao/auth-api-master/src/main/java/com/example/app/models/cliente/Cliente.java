package com.example.app.models.cliente;

import com.example.app.models.profissao.Profissao;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = Cliente.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente {
    public static final String TABLE_NAME = "cliente";
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "user_id")
    private String user;

    @Column(name="registroGeral",length = 100,nullable = false,unique = true)
    @NotEmpty
    @NotNull
    private String registroGeral;


    @Column(name="cpf",length = 100,nullable = false,unique = true)
    @NotEmpty
    @NotNull
    private String cpf;

    @Column(name="endereco",length = 255,nullable = false,unique = true)
    @NotEmpty
    @NotNull
    private String endereco;

    public Cliente(String cpf,String endereco,String registroGeral,String userId){
        this.cpf = cpf;
        this.endereco = endereco;
        this.registroGeral = registroGeral;
        this.user = userId;
    }

//    @OneToMany(mappedBy = "cliente")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private List<Profissao> profissoes;

}