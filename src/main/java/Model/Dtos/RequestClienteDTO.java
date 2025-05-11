package Model.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestClienteDTO {

    @NotBlank(message = "O nome do cliente é obrigatório.")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Size(min = 14 , max = 14, message = "O CNPJ deve conter exatamente 14 dígitos.")
    @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter apenas números.")
    private String cnpj;

    @NotBlank (message = "A atividade economica é obrigatória")
    private String atividadeEconomica;

    @NotBlank(message = "O nome do responsavel é obrigatório")
    private String responsavel;

}
