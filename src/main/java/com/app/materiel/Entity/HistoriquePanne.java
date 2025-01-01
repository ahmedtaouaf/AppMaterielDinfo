package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class HistoriquePanne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime datepanne;

    private String cause;

    private String observation;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HistoriqueEtat historiqueEtat;

    @ManyToOne
    @JoinColumn(name = "articlevsat_id", nullable = false)
    private ArticleVsat articleVsat;

}


