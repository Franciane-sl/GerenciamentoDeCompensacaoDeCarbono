package com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMaterialDTO {

    @NotBlank(message = "O nome do material é obrigatório.")
    private String nome;

    @PositiveOrZero(message = "O percentual de compensação não pode ter valores negativos.")
    private double percentualDeCompensacao;
}
