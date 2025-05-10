package Repository;

import Model.Entity.Declaracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclaracaoRepository extends JpaRepository<Declaracao , Long> {
}
