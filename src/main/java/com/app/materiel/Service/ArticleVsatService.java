package com.app.materiel.Service;

import com.app.materiel.Entity.ArticleVsat;
import com.app.materiel.Entity.HistoriqueEtat;
import com.app.materiel.Entity.HistoriquePanne;
import com.app.materiel.Repository.ArticleVsatRepository;
import com.app.materiel.Repository.HistoriquePanneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleVsatService {

    private final ArticleVsatRepository articleVsatRepository;
    private final HistoriquePanneRepository historiquePanneRepository;

    @Autowired
    public ArticleVsatService(ArticleVsatRepository articleVsatRepository, HistoriquePanneRepository historiquePanneRepository) {
        this.articleVsatRepository = articleVsatRepository;
        this.historiquePanneRepository = historiquePanneRepository;
    }

    public ArticleVsat getArticleById(Long articleId) {
        return articleVsatRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found with ID: " + articleId));
    }

    public void updateArticleStatus(Long articleId, HistoriqueEtat historiqueEtat, String cause, String observation) {
        ArticleVsat article = getArticleById(articleId);

        HistoriquePanne historiquePanne = new HistoriquePanne();
        historiquePanne.setArticleVsat(article);
        historiquePanne.setDatepanne(LocalDateTime.now());
        historiquePanne.setHistoriqueEtat(historiqueEtat);
        historiquePanne.setCause(cause);
        historiquePanne.setObservation(observation);

        historiquePanneRepository.save(historiquePanne);

        article.setStatus(historiqueEtat == HistoriqueEtat.REPARE);
        articleVsatRepository.save(article);
    }


}

