package com.app.materiel.Repository;

import com.app.materiel.Entity.ArticleVsat;
import com.app.materiel.Entity.HistoriquePanne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleVsatRepository extends JpaRepository<ArticleVsat, Long> {
}
