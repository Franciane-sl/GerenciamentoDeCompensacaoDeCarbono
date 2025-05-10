package Repository;

import Model.Entity.ItensDaDeclaracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensDaDeclaracaoRepository extends JpaRepository<ItensDaDeclaracao, Long> {

}
