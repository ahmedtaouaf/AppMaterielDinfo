package com.app.materiel.Service;

import com.app.materiel.Entity.Adressip;
import com.app.materiel.Entity.Stock;
import com.app.materiel.Entity.Type;
import com.app.materiel.Repository.AdressipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressipService {

    private final AdressipRepository adressipRepository;

    public AdressipService(AdressipRepository adressipRepository) {
        this.adressipRepository = adressipRepository;
    }

    public boolean existsByAdressip(String adressip) {
        return adressipRepository.existsByIp(adressip);
    }

    public void save(Adressip adressip) {
        adressipRepository.save(adressip);
    }

    public List<Adressip> findall() {
        return adressipRepository.findAll();
    }

    public Page<Adressip> findAdressesByResaux(String resaux, String searchTerm, int page) {
        Pageable pageable = PageRequest.of(page, 25);
        if (searchTerm == null || searchTerm.isEmpty()) {
            return adressipRepository.findAllByResaux(resaux, pageable);
        }
        return adressipRepository.findAllByResauxAndSearchTerm(resaux, searchTerm, pageable);
    }

    public Page<Adressip> findAdressesByResauxAndOrgane(String resaux, String searchTerm, Long organeId, int page) {
        Pageable pageable = PageRequest.of(page, 25);
        if ((searchTerm == null || searchTerm.isEmpty()) && organeId == null) {
            return adressipRepository.findAllByResaux(resaux, pageable);
        } else if (organeId == null) {
            return adressipRepository.findAllByResauxAndSearchTerm(resaux, searchTerm, pageable);
        } else if (searchTerm == null || searchTerm.isEmpty()) {
            return adressipRepository.findAllByResauxAndOrgane(resaux, organeId, pageable);
        }
        return adressipRepository.findAllByResauxAndSearchTermAndOrgane(resaux, searchTerm, organeId, pageable);
    }


    public void deleteAdressipById(Long id) {
        if (!adressipRepository.existsById(id)) {
            throw new RuntimeException("Adresse avec ID " + id + " introuvable.");
        }
        adressipRepository.deleteById(id);
    }


    public Page<Adressip> filterAdresses(String resaux, Long organe, Pageable pageable) {
        return adressipRepository.findByResauxNomAndOrganeId(resaux, organe, pageable);
    }

    public Page<Adressip> filterAdressesByResaux(String resaux, Pageable pageable) {
        return adressipRepository.findByResauxNom(resaux, pageable);
    }

    public Page<Adressip> filterAdressesByOrgane(Long organeId, Pageable pageable) {
        return adressipRepository.findByOrganeId(organeId, pageable);
    }

    public Page<Adressip> getAllAdresses(Pageable pageable) {
        return adressipRepository.findAll(pageable);
    }



}
