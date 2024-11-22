package com.app.materiel.Service;

import com.app.materiel.Entity.Division;
import com.app.materiel.Repository.DivisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {

    private final DivisionRepository divisionRepository;

    public DivisionService(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    public List<Division> findAll() {
        return divisionRepository.findAll();
    }
}
