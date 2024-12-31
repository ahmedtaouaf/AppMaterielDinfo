package com.app.materiel.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    private String reference;

    private String exercice;

    private double prixachat;

    @ManyToOne
    @JoinColumn(name = "situation_id", nullable = false)
    private Situation situation;

    @Transient
    private int progress;

    public void calculateProgress() {
        if (dateachat != null && dateexpiration != null) {
            LocalDate startDate = new java.sql.Date(dateachat.getTime()).toLocalDate();
            LocalDate endDate = new java.sql.Date(dateexpiration.getTime()).toLocalDate();
            LocalDate currentDate = LocalDate.now();

            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);

            if (totalDays > 0) {
                long daysPassed = ChronoUnit.DAYS.between(startDate, currentDate);
                this.progress = (int) ((daysPassed * 100) / totalDays);


                this.progress = Math.max(0, Math.min(100, this.progress));
            } else {
                this.progress = 0;
            }
        } else {
            this.progress = 0;
        }
    }
}
