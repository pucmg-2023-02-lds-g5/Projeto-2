package com.projeto2.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = PedidoAluguel.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PedidoAluguel {
    public static final String TABLE_NAME ="pedidoAluguel";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",unique = true)
    private Long id;
    @Column(name="statusAluguel",length = 60,nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAluguel status;
    @ManyToOne
    @JoinColumn(name="contratante_id")
    private Cliente contratante;
    @ManyToOne
    @JoinColumn(name="fornecedor_id")
    private Agente fornecedor;
    @ManyToOne
    @JoinColumn(name="automovel_id")
    private Automovel automovel;
    @Column(name = "dataInicio")
    @NotEmpty
    @NotNull
    private String dataInicio;
    @Column(name = "dataTermino")
    @NotEmpty
    @NotNull
    private String dataTermino;
}
