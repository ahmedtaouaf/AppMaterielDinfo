package com.app.materiel.Service;

import com.app.materiel.Entity.ArticleVsat;
import com.app.materiel.Entity.Poste;
import com.app.materiel.Repository.PosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosteService {

    private final PosteRepository posteRepository;

    @Autowired
    public PosteService(PosteRepository posteRepository) {
        this.posteRepository = posteRepository;
    }

    public List<Poste> getPostesByUniteResp(Long uniteRespId) {
        List<Poste> postes = posteRepository.findAll()
                .stream()
                .filter(poste -> poste.getUniteResp().getId().equals(uniteRespId))
                .toList();


        postes.forEach(poste -> {
            boolean isActive = poste.getArticles()
                    .stream()
                    .allMatch(ArticleVsat::getStatus);
            poste.setActive(isActive);
        });

        return postes;
    }

    public Poste getPosteById(Long posteId) {
        return posteRepository.findById(posteId)
                .orElseThrow(() -> new RuntimeException("Poste not found with ID: " + posteId));
    }
}
