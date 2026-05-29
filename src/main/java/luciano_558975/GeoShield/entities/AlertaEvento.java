package luciano_558975.GeoShield.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_alerta_evento")
public class AlertaEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_alerta", nullable = false)
    private String tipoAlerta;

    @Column(name = "probabilidade_tragedia", nullable = false)
    private Double probabilidadeTragedia;

    @Column(name = "nivel_risco", nullable = false)
    private NivelRisco nivelRisco;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @ManyToOne
    @JoinColumn(name = "regiao_id", nullable = false)
    private Regiao regiao;
}
