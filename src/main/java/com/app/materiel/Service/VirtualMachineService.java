package com.app.materiel.Service;

import com.app.materiel.Entity.Logiciel;
import com.app.materiel.Entity.Serveur;
import com.app.materiel.Entity.VirtualMachine;
import com.app.materiel.Repository.LogicielRepository;
import com.app.materiel.Repository.VirtualMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirtualMachineService {

    @Autowired
    private VirtualMachineRepository virtualMachineRepository;
    @Autowired
    private LogicielRepository logicielRepository;

    public VirtualMachine getVirtualMachineById(Long id) {
        return virtualMachineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Virtual Machine not found with id: " + id));
    }

    public List<VirtualMachine> getVirtualMachinesByServeur(Serveur serveur) {
        return virtualMachineRepository.findByServeur(serveur);
    }

    public List<Logiciel> getAllLogiciels() {
        return logicielRepository.findAll();
    }

    public void saveVirtualMachine(VirtualMachine virtualMachine) {
        virtualMachineRepository.save(virtualMachine);
    }
}
