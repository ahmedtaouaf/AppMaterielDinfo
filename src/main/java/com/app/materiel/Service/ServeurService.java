package com.app.materiel.Service;

import com.app.materiel.Entity.Serveur;
import com.app.materiel.Repository.ServeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServeurService {

    @Autowired
    private ServeurRepository serveurRepository;

    public Iterable<Serveur> getServersByResaux(String resaux) {
        return serveurRepository.findByResaux(resaux);
    }

    public Serveur getServerById(Long id) {
        return serveurRepository.findById(id).orElse(null);
    }

    public List<Serveur> getAllServers() {
        return serveurRepository.findAll();
    }

    public void saveServer(Serveur serveur) {
        serveurRepository.save(serveur);
    }
}
