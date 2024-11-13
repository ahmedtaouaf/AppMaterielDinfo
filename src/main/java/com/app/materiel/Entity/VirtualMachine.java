package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class VirtualMachine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;

    private String adresseip;

    private String cpu;

    private String ram;

    private String stockage;

    private String os;

    private String categorie;

    private String type;

    private String monitoringPath;

    @ManyToOne
    @JoinColumn(name = "logiciel_id", nullable = false)
    private Logiciel logiciel;

    @ManyToOne
    @JoinColumn(name = "serveur_id", nullable = false)
    private Serveur serveur;
}
