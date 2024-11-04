package com.app.materiel.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateachat;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateexpiration;

    @Column(unique = true)
    private String reference;

    private double prixachat;

    @ManyToOne
    @JoinColumn(name = "situation_id", nullable = false)
    private Situation situation ;

}
