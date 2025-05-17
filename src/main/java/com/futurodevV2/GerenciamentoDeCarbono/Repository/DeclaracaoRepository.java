package com.futurodevV2.GerenciamentoDeCarbono.Repository;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Entity.Declaracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclaracaoRepository extends JpaRepository<Declaracao , Long> {
}
