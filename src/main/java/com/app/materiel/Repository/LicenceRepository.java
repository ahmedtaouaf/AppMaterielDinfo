package com.app.materiel.Repository;

import com.app.materiel.Entity.Licence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenceRepository extends JpaRepository<Licence, Long> {
    @Query("SELECT l FROM Licence l WHERE l.situation.nom IN ('EXPIREE', 'PRESQUE') ORDER BY l.dateexpiration ASC")
    List<Licence> findExpiringLicenses();

    @Query("select count (l) from Licence l")
    int totalLicences();

    @Query("select count (l) from Licence l where l.situation.nom = 'EXPIREE'")
    int totalExpiree();

    @Query("select count (l) from Licence l where l.situation.nom = 'PRESQUE'")
    int totalPresque();
}
