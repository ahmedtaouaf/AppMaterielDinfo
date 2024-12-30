package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Poste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String dateactivation;

    private String lastonline;

    private String activatingkey;

    private String typevsat; // KA = Modem TooWay, KU = MODEM NEWTECK

    private String mac;

    private String adresseip;

    private String latitude;

    private String longitude;

    @ManyToOne
    @JoinColumn(name = "uniteresp_id", nullable = false)
    private UniteResp uniteResp;

    @OneToMany(mappedBy = "poste", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleVsat> articles = new ArrayList<>();

}


