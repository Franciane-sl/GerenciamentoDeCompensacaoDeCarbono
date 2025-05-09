package Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "itens_da_declaracao")
public class ItensDaDeclaracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "declaracao_id", foreignKey = @ForeignKey(name = "fk_itensDaDeclaracao_declaracao"))
    private Declaracao declaracao;
    @ManyToOne
    @JoinColumn(name = "material_id", foreignKey = @ForeignKey(name = "fk_itensDaDeclaracao_material"))
    private Material material;
    @Column(nullable = false)
    private double percentualDeCompensacao;
    @Column(nullable = false)
    private double toneladasCompensacao;
}
