package luciano_558975.GeoShield.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private String nome;
    private String coordenadas;
    private Integer populacaoAfetada;
    private LocalDate dataMapeamento;

    @OneToMany(mappedBy = "regiao")
    private List<AlertaEvento> alertas = new ArrayList<>();
}
