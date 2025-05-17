package com.futurodevV2.GerenciamentoDeCarbono.Model.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDeclaracaoDTO {

    @NotNull(message = "O id do cliente é obrigatório.")
    private Long clienteId;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataInicialDoPeriodo;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataFinalDoPeriodo;

    private List<RequestItensDaDeclaracaoDTO> itens;
}
