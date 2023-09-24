package com.example.app.models.profissao;

import com.example.app.models.cliente.Cliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Double salario;
    @Column
    @NotNull
    private String user_id;

    public Profissao(String empregador,String nome,Double salario){
        this.empregador = empregador;
        this.nome = nome;
        this.salario = salario;
    }

//    @Column(name = "cliente_id")
//    private String cliente;


}