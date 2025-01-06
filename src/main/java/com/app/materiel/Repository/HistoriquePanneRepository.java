package com.app.materiel.Repository;

import com.app.materiel.Entity.HistoriquePanne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriquePanneRepository extends JpaRepository<HistoriquePanne, Long> {

    List<HistoriquePanne> findByArticleVsatIdOrderByDatepanneDesc(Long articleId);
}
