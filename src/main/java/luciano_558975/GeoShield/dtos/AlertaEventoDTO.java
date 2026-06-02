package luciano_558975.GeoShield.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import luciano_558975.GeoShield.entities.AlertaEvento;
import luciano_558975.GeoShield.entities.NivelRisco;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AlertaEventoDTO {
    private Long id;

    @NotBlank(message = "O campo 'Tipo Alerta' é obrigatório.")
    @Size(min = 3, max = 50, message = "O campo 'Tipo Alerta' deve ter entre 3 a 50 caracteres.")
    private String tipoAlerta;

    @NotNull(message = "O campo 'Probabilidade Tragedia' é obrigatório.")
    @Positive(message = "O campo 'Probabilidade Tragedia' deve ser maior que 0")
    @Max(100)
    private Double probabilidadeTragedia;

    @NotNull(message = "O campo 'Data Emissao' é obrigatório.")
    @PastOrPresent(message = "A data de emissão não pode estar no futuro.")
    private LocalDate dataEmissao;

    private NivelRisco nivelRisco;

    @NotNull(message = "O campo 'Regiao' é obrigatório.")
    private RegiaoDTO regiao;

    public AlertaEventoDTO(AlertaEvento a) {
        this.id = a.getId();
        this.tipoAlerta = a.getTipoAlerta();
        this.probabilidadeTragedia = a.getProbabilidadeTragedia();
        this.dataEmissao = a.getDataEmissao();
        this.nivelRisco = a.getNivelRisco();
        this.regiao = new RegiaoDTO(a.getRegiao());
    }
}
