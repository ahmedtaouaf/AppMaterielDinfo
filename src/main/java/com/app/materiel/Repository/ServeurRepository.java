package com.app.materiel.Repository;

import com.app.materiel.Entity.Serveur;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ServeurRepository extends JpaRepository<Serveur, Long> {

    List<Serveur> findByResaux(String resaux);
}
