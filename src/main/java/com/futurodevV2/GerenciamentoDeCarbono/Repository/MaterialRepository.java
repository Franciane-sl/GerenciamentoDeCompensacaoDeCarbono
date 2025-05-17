package com.futurodevV2.GerenciamentoDeCarbono.Repository;

import com.futurodevV2.GerenciamentoDeCarbono.Model.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    
}
