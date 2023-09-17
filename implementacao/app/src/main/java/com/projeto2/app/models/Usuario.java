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

import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class Usuario {
    public interface CreateUser{}
    public interface UpdateUser{}

    public static final String TABLE_NAME="usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",unique = true)
    private Long id;


    @Column(name="login",length = 100,nullable = false,unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size( groups = CreateUser.class,min=2,max=100)
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="senha",length = 60,nullable = false)
    @NotNull(groups = {CreateUser.class,UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class,UpdateUser.class})
    @Size(groups = {CreateUser.class,UpdateUser.class},min=3,max=60)
    private String senha;


}
