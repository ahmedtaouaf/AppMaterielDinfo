package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Mouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datesortie;

    private Date dateentree;

    private Integer quantitemvn;

    private String observation;

    @ManyToOne
    @JoinColumn(name = "etatmouvement_id", nullable = false)
    private EtatMouvement etatMouvement;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "responsable_id", nullable = false)
    private Responsable responsable;

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;


}

