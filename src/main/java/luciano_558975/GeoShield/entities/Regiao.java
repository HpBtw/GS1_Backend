package luciano_558975.GeoShield.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    @Column(nullable = false, length = 30)
    private String nome;

    @Column(nullable = false, length = 40)
    private String coordenadas;

    @Column(name = "populacao_afetada", nullable = false)
    private Integer populacaoAfetada;

    @Column(name = "data_mapeamento", nullable = false)
    private LocalDate dataMapeamento;

    @JsonIgnore
    @OneToMany(mappedBy = "regiao")
    private List<AlertaEvento> alertas = new ArrayList<>();
}
