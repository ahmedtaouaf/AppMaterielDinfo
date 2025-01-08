package com.app.materiel.Repository;

import com.app.materiel.Entity.Responsable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {
    Optional<Responsable> findByNom(String nom);
}
