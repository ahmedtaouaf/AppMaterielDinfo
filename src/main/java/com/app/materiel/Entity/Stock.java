package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;
    private String nserie;
    private String observation;
    private Date datee;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

}
