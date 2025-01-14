package com.app.materiel.Repository;

import com.app.materiel.Entity.ArticleVsat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleVsatRepository extends JpaRepository<ArticleVsat, Long> {
    @Query("SELECT a.typeArticleVsat, COUNT(a) FROM ArticleVsat a WHERE a.status = false GROUP BY a.typeArticleVsat")
    List<Object[]> findArticlesWithStatusZero();


}
