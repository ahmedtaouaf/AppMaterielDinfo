package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Serveur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String adresseip;

    private String cpu;

    private String ram;

    private String stockage;

    private String hyperviseur;

    private String nserie;

    private String resaux;

    @OneToMany(mappedBy = "serveur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VirtualMachine> virtualMachines;
}