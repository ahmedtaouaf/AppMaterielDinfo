package com.app.materiel.Service;

import com.app.materiel.Entity.UniteResp;
import com.app.materiel.Repository.UniteRespRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniteRespService {

    private final UniteRespRepository uniteRespRepository;

    @Autowired
    public UniteRespService(UniteRespRepository uniteRespRepository) {
        this.uniteRespRepository = uniteRespRepository;
    }

    public List<UniteResp> getAllUniteResp() {
        return uniteRespRepository.findAll();
    }
}
