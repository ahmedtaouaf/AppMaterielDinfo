package com.app.materiel.Service;

import com.app.materiel.Entity.Organe;
import com.app.materiel.Repository.OrganeRepository;
import org.springframework.stereotype.Service;

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
}

