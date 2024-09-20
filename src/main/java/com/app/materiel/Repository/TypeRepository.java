package com.app.materiel.Repository;

import com.app.materiel.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    @Query("select count (t) from Type t")
    int totalType();
}
