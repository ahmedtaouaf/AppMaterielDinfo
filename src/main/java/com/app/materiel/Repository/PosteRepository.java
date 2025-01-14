package com.app.materiel.Repository;

import com.app.materiel.Entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {

    @Query("select count (p) from Poste p")
    int totalPoste();

    @Query("select count (p) from Poste p where p.typevsat='KU'")
    int totalKu();

    @Query("select count (p) from Poste p where p.typevsat='KA'")
    int totalKa();

    @Query("SELECT COUNT(p) FROM Poste p WHERE " +
            "NOT EXISTS (SELECT a FROM ArticleVsat a WHERE a.poste = p AND a.status = FALSE)")
    Long countUpPostes();

    @Query("SELECT COUNT(p) FROM Poste p WHERE " +
            "EXISTS (SELECT a FROM ArticleVsat a WHERE a.poste = p AND a.status = FALSE)")
    Long countDownPostes();
}
