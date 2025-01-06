package com.app.materiel.Service;

import com.app.materiel.Entity.HistoriquePanne;
import com.app.materiel.Repository.HistoriquePanneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriquePanneService {

    private final HistoriquePanneRepository historiquePanneRepository;

    @Autowired
    public HistoriquePanneService(HistoriquePanneRepository historiquePanneRepository) {
        this.historiquePanneRepository = historiquePanneRepository;
    }

    public List<HistoriquePanne> getHistoryByArticleId(Long articleId) {
        return historiquePanneRepository.findByArticleVsatIdOrderByDatepanneDesc(articleId);
    }
}

