package com.app.materiel.Repository;

import com.app.materiel.Entity.HistoriquePanne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriquePanneRepository extends JpaRepository<HistoriquePanne, Long> {
}
