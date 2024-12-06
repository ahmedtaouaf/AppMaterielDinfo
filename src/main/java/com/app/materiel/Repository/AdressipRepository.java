package com.app.materiel.Repository;

import com.app.materiel.Entity.Adressip;
import com.app.materiel.Entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressipRepository extends JpaRepository<Adressip, Long> {
    boolean existsByIp(String adressip);

    @Query("SELECT a FROM Adressip a WHERE a.resaux.nom= 'INTERNET' AND (:searchTerm IS NULL OR :searchTerm = '' OR a.ip LIKE %:searchTerm% OR a.service LIKE %:searchTerm% OR a.organe.nom LIKE %:searchTerm% OR a.division.Designation LIKE %:searchTerm% )")
    Page<Adressip> findAllBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);

    Page<Adressip> findByResauxNomAndOrganeId(String resaux, Long organeId, Pageable pageable);

    Page<Adressip> findByResauxNom(String resaux, Pageable pageable);

    Page<Adressip> findByOrganeId(Long organeId, Pageable pageable);

    @Query("SELECT a FROM Adressip a WHERE a.resaux.nom= 'INTERNET'")
    Page<Adressip> findAllByResaux(Pageable pageable);


}

