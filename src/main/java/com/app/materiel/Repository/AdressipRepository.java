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


    Page<Adressip> findByResauxNomAndOrganeId(String resaux, Long organeId, Pageable pageable);

    Page<Adressip> findByResauxNom(String resaux, Pageable pageable);

    Page<Adressip> findByOrganeId(Long organeId, Pageable pageable);

    @Query("SELECT a FROM Adressip a WHERE a.resaux.nom = :resaux")
    Page<Adressip> findAllByResaux(@Param("resaux") String resaux, Pageable pageable);

    @Query("SELECT a FROM Adressip a WHERE a.resaux.nom = :resaux AND " +
            "(:searchTerm IS NULL OR :searchTerm = '' OR " +
            "a.ip LIKE %:searchTerm% OR a.service LIKE %:searchTerm% OR " +
            "a.organe.nom LIKE %:searchTerm% OR a.division.Designation LIKE %:searchTerm%)")
    Page<Adressip> findAllByResauxAndSearchTerm(@Param("resaux") String resaux,
                                                @Param("searchTerm") String searchTerm,
                                                Pageable pageable);

    @Query("SELECT a FROM Adressip a WHERE a.resaux.nom = :resaux AND a.organe.id = :organeId")
    Page<Adressip> findAllByResauxAndOrgane(@Param("resaux") String resaux,
                                            @Param("organeId") Long organeId,
                                            Pageable pageable);

    @Query("SELECT a FROM Adressip a WHERE a.resaux.nom = :resaux AND " +
            "(:searchTerm IS NULL OR :searchTerm = '' OR " +
            "a.ip LIKE %:searchTerm% OR a.service LIKE %:searchTerm% OR " +
            "a.organe.nom LIKE %:searchTerm% OR a.division.Designation LIKE %:searchTerm%) " +
            "AND (:organeId IS NULL OR a.organe.id = :organeId)")
    Page<Adressip> findAllByResauxAndSearchTermAndOrgane(@Param("resaux") String resaux,
                                                         @Param("searchTerm") String searchTerm,
                                                         @Param("organeId") Long organeId,
                                                         Pageable pageable);



}

