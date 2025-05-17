package com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMaterialDTO {

    private Long id;
    private String nome;
    private double  percentualDeCompensacao;
}
