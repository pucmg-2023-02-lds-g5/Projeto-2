package com.example.app.models.agente;

import com.example.app.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Table(name = Agente.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Agente {
    public static final String TABLE_NAME = "agente";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="tipoAgente",length = 60,nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAgente tipoAgente;


    @Column(name = "usuario_id")
    private String user;

    public Agente(TipoAgente agente,String user){
        setTipoAgente(agente);
        setUser(user);
    }
}
