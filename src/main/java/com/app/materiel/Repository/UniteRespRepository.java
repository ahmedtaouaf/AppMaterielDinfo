package com.app.materiel.Repository;

import com.app.materiel.Entity.UniteResp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniteRespRepository extends JpaRepository<UniteResp, Long> {}
