package luciano_558975.GeoShield.entities;

import jakarta.persistence.*;
import lombok.*;
import luciano_558975.GeoShield.dtos.NivelRiscoDTO;

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

    @Column(name = "tipo_alerta", nullable = false, length = 50)
    private String tipoAlerta;

    @Column(name = "probabilidade_tragedia", nullable = false)
    private Double probabilidadeTragedia;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_risco", length = 15, nullable = false)
    private NivelRisco nivelRisco;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "regiao_id")
    private Regiao regiao;
}
