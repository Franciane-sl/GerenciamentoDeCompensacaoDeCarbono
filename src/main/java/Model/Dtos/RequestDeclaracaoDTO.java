package Model.Dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDeclaracaoDTO {

    @NotNull(message = "O id do cliente é obrigatório.")
    private Long clienteId;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @FutureOrPresent(message = "A data da declaração deve ser atual ou estar futuro.")
    private LocalDate dataDaDeclaracao;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataInicialDoPeriodo;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataFinalDoPeriodo;

    @PositiveOrZero(message = "O total de materiais não pode ser negativo.")
    private double totalDeMateriais;

    @PositiveOrZero(message = "O total de compensação deve ser positivo.")
    private double totalDeCompensacao;
}
