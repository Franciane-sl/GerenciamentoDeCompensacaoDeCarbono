package com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseItensDaDeclaracaoDTO {

    private Long id;
    private ResponseDeclaracaoDTO declaracao;
    private ResponseMaterialDTO material;
    private double percentualDeCompensacao;
    private double toneladasDeclaradas;
    private double toneladasCompensacao;
}
