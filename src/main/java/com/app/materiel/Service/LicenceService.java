package com.app.materiel.Service;

import com.app.materiel.Entity.*;
import com.app.materiel.Repository.LicenceRepository;
import com.app.materiel.Repository.SituationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public void saveLicence(Licence licence) {
        licenceRepository.save(licence);
    }

    public Licence findById(Long id) {
        return licenceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Licence not found with id " + id));
    }


}
