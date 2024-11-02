package com.app.materiel.Repository;

import com.app.materiel.Entity.Stock;
import com.app.materiel.Entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s WHERE (:searchTerm IS NULL OR :searchTerm = '' OR s.nserie LIKE %:searchTerm% OR s.designation LIKE %:searchTerm%) ORDER BY s.datee DESC")
    Page<Stock> findAllBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);

    List<Stock> findByTypeId(Long typeId);

    List<Stock> findByTypeIdAndStatusLibelle(Long typeId, String statusLibelle);

    List<Stock> findByStatusLibelle(String libelle);

    List<Stock> findAll();

    List<Stock> findByTypeLibelleAndStatusLibelle(String type, String status);


    @Query("SELECT s.type.libelle, COUNT(s) FROM Stock s GROUP BY s.type")
    List<Object[]> countStocksByType();

    @Query("select count (s) from Stock s")
    int totalStock();

    List<Stock> findByType(Type type);

    boolean existsByNserie(String nserie);








}
