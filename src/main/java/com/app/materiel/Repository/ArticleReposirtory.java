package com.app.materiel.Repository;

import com.app.materiel.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleReposirtory extends JpaRepository<Article, Long> {
}
