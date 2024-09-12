package com.app.materiel.Service;

import com.app.materiel.Entity.Responsable;
import com.app.materiel.Repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsableService {

    @Autowired
    private ResponsableRepository responsableRepository;

    public void addResponsable(Responsable responsable){
        responsableRepository.save(responsable);
    }
    public List<Responsable> findResponsable(){
        return responsableRepository.findAll();
    }

}
