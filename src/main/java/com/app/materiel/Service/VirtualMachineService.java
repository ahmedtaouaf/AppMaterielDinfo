package com.app.materiel.Service;

import com.app.materiel.Entity.Serveur;
import com.app.materiel.Entity.VirtualMachine;
import com.app.materiel.Repository.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirtualMachineService {

    @Autowired
    private VirtualMachineRepository virtualMachineRepository;

    public List<VirtualMachine> getVirtualMachinesByServeur(Serveur serveur) {
        return virtualMachineRepository.findByServeur(serveur);
    }
}
