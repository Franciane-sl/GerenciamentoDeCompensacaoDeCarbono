package Repository;

import Model.Entity.ItensDaDeclaracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItensDaDeclaracaoRepository extends JpaRepository<ItensDaDeclaracao, Long> {

    @Query("SELECT i FROM ItensDaDeclaracao i " +
            "WHERE i.declaracao.cliente.id = :clienteId " +
            "AND i.declaracao.dataInicialDoPeriodo >= :dataInicial " +
            "AND i.declaracao.dataFinalDoPeriodo <= :dataFinal ")
    List<ItensDaDeclaracao> findByClienteEPeriodo(
            @Param("clienteId") Long clienteID,
            @Param("dataInicial") LocalDate dataInicial,
            @Param("dataFinal") LocalDate dataFinal
            );
}
