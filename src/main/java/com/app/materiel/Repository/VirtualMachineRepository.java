package com.app.materiel.Repository;

import com.app.materiel.Entity.Serveur;
import com.app.materiel.Entity.VirtualMachine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirtualMachineRepository extends JpaRepository<VirtualMachine, Long> {
    List<VirtualMachine> findByServeur(Serveur serveur);
}
