package Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClienteDTO {

    private Long id;
    private String nome;
    private String cnpj;
    private String atividadeEconomica;
    private String responsavel;
}
