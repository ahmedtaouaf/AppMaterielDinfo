package com.app.materiel.Service;

import com.app.materiel.Entity.Licence;
import com.app.materiel.Entity.Organe;
import com.app.materiel.Repository.OrganeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrganeService {

    private final OrganeRepository organeRepository;

    public OrganeService(OrganeRepository organeRepository) {
        this.organeRepository = organeRepository;
    }

    public List<Organe> findAll() {
        return organeRepository.findAll();
    }

    public Organe findById(Long id) {
        return organeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organe not found with id " + id));
    }
}

