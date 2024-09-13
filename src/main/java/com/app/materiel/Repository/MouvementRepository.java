package com.app.materiel.Repository;

import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MouvementRepository extends JpaRepository<Mouvement, Long> {

    @Query("SELECT m FROM Mouvement m WHERE (:searchTerm IS NULL OR :searchTerm = '' OR m.position.libelle LIKE %:searchTerm% OR m.status.libelle LIKE %:searchTerm%) AND m.status.libelle <> 'DISPONIBLE' ORDER BY m.datee DESC")
    Page<Mouvement> findAllBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT m FROM Mouvement m WHERE m.status.libelle <> 'DISPONIBLE' ORDER BY m.datee DESC")
    Page<Mouvement> findAllmvns(Pageable pageable);

    @Query("SELECT m FROM Mouvement m WHERE (:searchTerm IS NULL OR :searchTerm = '' OR m.position.libelle LIKE %:searchTerm% OR m.status.libelle LIKE %:searchTerm%) AND m.status.libelle = 'ENTREE AU STOCK' ORDER BY m.datee DESC")
    Page<Mouvement> findAllBySearchTermEntree(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT m FROM Mouvement m WHERE m.status.libelle = 'ENTREE AU STOCK' ORDER BY m.datee DESC")
    Page<Mouvement> findAllmvnsEntree(Pageable pageable);

    Mouvement findTopByStockOrderByDateeDesc(Stock stock);


}
