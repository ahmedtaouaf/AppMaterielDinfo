package com.app.materiel.Service;

import com.app.materiel.Entity.Position;
import com.app.materiel.Entity.Responsable;
import com.app.materiel.Repository.PositionRepository;
import com.app.materiel.Repository.ResponsableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;
    public List<Position> findPosition(){
        return positionRepository.findAll();
    }
    public void addPosition(Position position){
        positionRepository.save(position);
    }


}
