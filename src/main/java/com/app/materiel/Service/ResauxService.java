package com.app.materiel.Service;

import com.app.materiel.Entity.Resaux;
import com.app.materiel.Repository.ResauxRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResauxService {

    private final ResauxRepository resauxRepository;

    public ResauxService(ResauxRepository resauxRepository) {
        this.resauxRepository = resauxRepository;
    }

    public List<Resaux> findAll() {
        return resauxRepository.findAll();
    }
}

