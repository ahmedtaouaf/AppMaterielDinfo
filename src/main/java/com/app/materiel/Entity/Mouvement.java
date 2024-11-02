package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Mouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date datee;

    private Date dateentree;

    private String observation;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;
    @ManyToOne
    @JoinColumn(name = "responsable_id", nullable = false)
    private Responsable responsable;
}
