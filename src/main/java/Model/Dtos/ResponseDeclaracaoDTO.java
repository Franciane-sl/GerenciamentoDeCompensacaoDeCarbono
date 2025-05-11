package Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDeclaracaoDTO {

    private Long id;
    private ResponseClienteDTO cliente;
    private LocalDate dataDaDeclaracao;
    private LocalDate dataInicialDoPeriodo;
    private LocalDate dataFinalDoPeriodo;
    private double totalDeMateriais;
    private double totalDeCompensacao;
}
