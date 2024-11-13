package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Logiciel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;


}
