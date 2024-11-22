package com.app.materiel.Repository;

import com.app.materiel.Entity.Adressip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressipRepository extends JpaRepository<Adressip, Long> {
    boolean existsByAdressip(String adressip);
}

