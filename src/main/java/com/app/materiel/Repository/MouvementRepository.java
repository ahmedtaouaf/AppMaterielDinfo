package com.app.materiel.Repository;

import com.app.materiel.Entity.Mouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MouvementRepository extends JpaRepository<Mouvement, Long> {
}
