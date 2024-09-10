package com.app.materiel.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Appuser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private boolean enabled;

}

