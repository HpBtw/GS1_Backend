package luciano_558975.GeoShield.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import luciano_558975.GeoShield.entities.Regiao;

import java.time.LocalDate;

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

    public RegiaoDTO(Regiao r) {
        this.id = r.getId();
        this.nome = r.getNome();
        this.coordenadas = r.getCoordenadas();
        this.populacaoAfetada = r.getPopulacaoAfetada();
        this.dataMapeamento = r.getDataMapeamento();
    }
}
