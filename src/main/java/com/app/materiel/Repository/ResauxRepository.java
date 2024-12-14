package com.app.materiel.Repository;

import com.app.materiel.Entity.Resaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResauxRepository extends JpaRepository<Resaux, String> {

}

