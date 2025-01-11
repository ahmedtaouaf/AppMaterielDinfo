package com.app.materiel.Repository;

import com.app.materiel.Entity.Poste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteRepository extends JpaRepository<Poste, Long> {

    @Query("select count (p) from Poste p")
    int totalPoste();
}
