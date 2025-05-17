package com.futurodevV2.GerenciamentoDeCarbono.Model.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "declaracoes")
public class Declaracao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_declaracao_cliente"))
    private Cliente cliente;
    @Column(nullable = false)
    private LocalDate dataDaDeclaracao;
    @Column(nullable = false)
    private LocalDate dataInicialDoPeriodo;
    @Column(nullable = false)
    private LocalDate dataFinalDoPeriodo;
    @Column(nullable = false)
    private double totalDeMateriais;
    @Column(nullable = false)
    private double totalDeCompensacao;
    @OneToMany(mappedBy = "declaracao")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ItensDaDeclaracao> itensDasDeclaracoes;
}
