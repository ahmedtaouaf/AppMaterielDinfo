package com.app.materiel.Service;

import com.app.materiel.Entity.Adressip;
import com.app.materiel.Entity.Type;
import com.app.materiel.Repository.AdressipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressipService {

    private final AdressipRepository adressipRepository;

    public AdressipService(AdressipRepository adressipRepository) {
        this.adressipRepository = adressipRepository;
    }

    public boolean existsByAdressip(String adressip) {
        return adressipRepository.existsByAdressip(adressip);
    }

    public void save(Adressip adressip) {
        adressipRepository.save(adressip);
    }

    public List<Adressip> findall() {
        return adressipRepository.findAll();
    }


}
