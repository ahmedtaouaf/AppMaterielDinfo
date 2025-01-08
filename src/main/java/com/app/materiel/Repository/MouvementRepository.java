package com.app.materiel.Repository;

import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface MouvementRepository extends JpaRepository<Mouvement, Long> {

    @Query("SELECT m FROM Mouvement m WHERE (:searchTerm IS NULL OR :searchTerm = '' OR m.position.libelle LIKE %:searchTerm% OR m.stock.type.libelle LIKE %:searchTerm% OR m.stock.designation LIKE %:searchTerm% OR m.stock.nserie LIKE %:searchTerm%) AND m.status.libelle NOT IN ('DISPONIBLE', 'INDISPONIBLE') and m.dateentree is null ORDER BY m.datee DESC")
    Page<Mouvement> findAllBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT m FROM Mouvement m WHERE m.status.libelle = 'MISSION' and m.dateentree is null ORDER BY m.datee DESC")
    Page<Mouvement> findAllmvns(Pageable pageable);

    @Query("SELECT m FROM Mouvement m WHERE m.status.libelle = 'EN PANNE' and m.dateentree is null ORDER BY m.datee DESC")
    Page<Mouvement> findAllPanne(Pageable pageable);

    @Query("SELECT m FROM Mouvement m WHERE (:searchTerm IS NULL OR :searchTerm = '' OR m.position.libelle LIKE %:searchTerm% OR m.status.libelle LIKE %:searchTerm%) AND m.status.libelle = 'ENTREE AU STOCK' ORDER BY m.datee DESC")
    Page<Mouvement> findAllBySearchTermEntree(@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT m FROM Mouvement m WHERE m.status.libelle NOT IN ('DISPONIBLE', 'INDISPONIBLE', 'EN PANNE') and m.dateentree is null ORDER BY m.datee DESC")
    Page<Mouvement> findAllmvnsEntree(Pageable pageable);

    Mouvement findTopByStockOrderByDateeDesc(Stock stock);


    Page<Mouvement> findByStockOrderByDateeDescDateentreeDesc(Stock stock, Pageable pageable);

    List<Mouvement> findTop6ByOrderByDateeDesc();

    @Query("select count (m) from Mouvement m")
    int totalMvn();

    @Query("SELECT m.stock.type.libelle, COUNT(m) FROM Mouvement m GROUP BY m.stock.type.libelle")
    List<Object[]> countMouvementsByType();

    @Query("SELECT FUNCTION('DAYNAME', m.datee), COUNT(m) FROM Mouvement m WHERE m.datee IS NOT NULL GROUP BY FUNCTION('DAYNAME', m.datee)")
    List<Object[]> countMouvementByDay();


    @Transactional
    void deleteByStockId(Long stockId);
    List<Mouvement> findByDateeBetweenOrDateentreeBetween(Date startDate, Date endDate, Date startDateEntree, Date endDateEntree);








}
