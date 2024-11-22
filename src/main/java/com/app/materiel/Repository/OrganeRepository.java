package com.app.materiel.Repository;

import com.app.materiel.Entity.Organe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganeRepository extends JpaRepository<Organe, Long> {
}

