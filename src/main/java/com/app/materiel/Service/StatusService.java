package com.app.materiel.Service;

import com.app.materiel.Entity.Status;
import com.app.materiel.Entity.Type;
import com.app.materiel.Repository.StatusRepository;
import com.app.materiel.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;
    public List<Status> findstatus(){
        return statusRepository.findAll();
    }

}
