package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ArticleVsat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    private Boolean status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeArticleVsat typeArticleVsat;

    @ManyToOne
    @JoinColumn(name = "poste_id", nullable = false)
    private Poste poste;
}


