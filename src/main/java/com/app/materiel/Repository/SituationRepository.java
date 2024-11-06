package com.app.materiel.Repository;

import com.app.materiel.Entity.Licence;
import com.app.materiel.Entity.Situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Long> {

    Optional<Situation> findByNom(String nom);


}
