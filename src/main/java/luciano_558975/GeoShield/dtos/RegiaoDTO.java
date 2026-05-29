package luciano_558975.GeoShield.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import luciano_558975.GeoShield.entities.AlertaEvento;
import luciano_558975.GeoShield.entities.Regiao;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegiaoDTO {
    private Long id;
    @NotBlank(message = "O campo 'Nome' é obrigatório.")
    @Size(min = 3, max = 30, message = "O campo 'Nome' deve ter entre 3 a 30 caracteres.")
    private String nome;

    @NotBlank(message = "O campo 'Coordenadas' é obrigatório.")
    @Size(min = 2, message = "O campo 'Coordenadas' deve conter pelo menos 2 caracteres.")
    private String coordenadas;

    @NotNull(message = "O campo 'Populacao Afetada' é obrigatório.")
    @PositiveOrZero(message = "O campo 'Populacao Afetada' deve ser igual ou maior que 0.")
    private Integer populacaoAfetada;

    @NotNull(message = "O campo 'Data Mapeamento' é obrigatório.")
    @PastOrPresent(message = "O campo 'Data Mapeamento' deve conter a data atual ou anterior.")
    private LocalDate dataMapeamento;

    private List<@Valid AlertaEventoDTO> alertas;

    public RegiaoDTO(Regiao r) {
        this.id = r.getId();
        this.coordenadas = r.getCoordenadas();
        this.dataMapeamento = r.getDataMapeamento();
        this.nome = r.getNome();
        this.populacaoAfetada = r.getPopulacaoAfetada();

        for (AlertaEvento alerta : r.getAlertas()) {
            AlertaEventoDTO dto = new AlertaEventoDTO(alerta);
            alertas.add(dto);
        }
    }
}
