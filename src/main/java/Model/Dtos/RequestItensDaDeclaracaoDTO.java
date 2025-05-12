package Model.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestItensDaDeclaracaoDTO {

    @NotNull(message = "O id da declaração é obrigatório.")
    private Long declaracaoID;

    @NotNull(message = "O id do material é obrigatório.")
    private Long materialId;

    @PositiveOrZero(message = "O percentual de compensação não pode ter valor negativo.")
    private double percentualDeCompensacao;

    @PositiveOrZero(message = "As toneladas declaradas não podem ter valores negativos.")
    private double toneladasDeclaradas;

    @PositiveOrZero(message = "As toneladas de compensação não podem ter valores negativos.")
    private double toneladasCompensacao;
}
