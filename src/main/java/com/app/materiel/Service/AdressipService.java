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

    public Page<Adressip> findAllAdresses(String searchTerm, int page) {
        Pageable pageable = PageRequest.of(page, 25);
        if (searchTerm == null || searchTerm.isEmpty()) {
            return adressipRepository.findAll(pageable);
        }
        return adressipRepository.findAllBySearchTerm(searchTerm, pageable);
    }


}
