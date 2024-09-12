package com.app.materiel.Service;

import com.app.materiel.Entity.Position;
import com.app.materiel.Entity.Type;
import com.app.materiel.Repository.PositionRepository;
import com.app.materiel.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;
    public List<Type> findtypes(){
        return typeRepository.findAll();
    }
}
