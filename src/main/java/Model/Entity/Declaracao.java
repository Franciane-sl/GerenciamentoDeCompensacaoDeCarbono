package Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

}
