package com.example.app.models.pedidoAluguel;

//import com.example.app.models.automovel.Automovel;
import com.example.app.models.user.User;
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
    @Column(name="contratante_id")
    private String contratante;
    @Column(name="fornecedor_id")
    private String fornecedor;
    @Column(name="automovel_placa")
    private String placaAutomovel;
    @Column(name = "dataInicio")
    @NotEmpty
    @NotNull
    private String dataInicio;
    @Column(name = "dataTermino")
    @NotEmpty
    @NotNull
    private String dataTermino;
}
