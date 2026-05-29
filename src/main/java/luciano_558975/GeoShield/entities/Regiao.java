package luciano_558975.GeoShield.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_regiao")
public class Regiao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String coordenadas;

    @Column(name = "populacao_afetada", nullable = false)
    private Integer populacaoAfetada;

    @Column(name = "data_mapeamento", nullable = false)
    private LocalDate dataMapeamento;

    @OneToMany(mappedBy = "regiao")
    private List<AlertaEvento> alertas = new ArrayList<>();
}
