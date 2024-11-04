package com.app.materiel.Service;

import com.app.materiel.Entity.*;
import com.app.materiel.Repository.LicenceRepository;
import com.app.materiel.Repository.SituationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenceService {

    @Autowired
    private LicenceRepository licenceRepository;
    @Autowired
    private SituationRepository situationRepository;

    public List<Licence> findLicence(){
        return licenceRepository.findAll();
    }

    public void addLicence(Licence licence){
        Situation situation = situationRepository.findById(2L).orElseThrow();
        licence.setSituation(situation);
        licenceRepository.save(licence);
    }

}
