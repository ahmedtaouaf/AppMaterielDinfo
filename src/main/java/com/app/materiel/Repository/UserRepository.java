package com.app.materiel.Repository;

import com.app.materiel.Entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Appuser, Long> {
    Optional<Appuser> findByUsername(String username);
}
