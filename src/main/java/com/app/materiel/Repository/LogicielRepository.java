package com.app.materiel.Repository;

import com.app.materiel.Entity.Logiciel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogicielRepository extends JpaRepository<Logiciel, Long> {
}
