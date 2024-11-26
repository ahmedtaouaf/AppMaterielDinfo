package com.app.materiel.Entity;

import lombok.Data;
import org.sonatype.inject.Nullable;


import javax.persistence.*;

@Entity
@Data
public class Adressip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ip;
    private String designation;
    private String mac;
    private String responsable;
    private String service;
    private String materiel;
    private Boolean temporaire;

    @ManyToOne
    private Division division;

    @ManyToOne
    private Resaux resaux;

    @ManyToOne
    @JoinColumn(name = "organe_id", nullable = false)
    private Organe organe;
}
