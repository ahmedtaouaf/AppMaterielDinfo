package com.app.materiel.Service;

import com.app.materiel.Entity.Adressip;
import com.app.materiel.Entity.Licence;
import com.app.materiel.Entity.Organe;
import com.app.materiel.Repository.AdressipRepository;
import com.app.materiel.Repository.OrganeRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrganeService {

    private final OrganeRepository organeRepository;
    private final AdressipRepository adressipRepository;

    public OrganeService(OrganeRepository organeRepository, AdressipRepository adressipRepository) {
        this.organeRepository = organeRepository;
        this.adressipRepository = adressipRepository;
    }

    public List<Organe> findAll() {
        return organeRepository.findAll();
    }

    public Organe findById(Long id) {
        return organeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Organe not found with id " + id));
    }

    public Map<String, Object> getOrganeTree(String organeName) {
        List<Adressip> adresses = adressipRepository.findByOrgane(organeName);

        // Group by Resaux first
        Map<String, List<Adressip>> groupedByResaux = adresses.stream()
                .filter(a -> a.getResaux() != null) // Ensure Resaux is not null
                .collect(Collectors.groupingBy(a -> a.getResaux().getNom()));

        // For each Resaux, group by Division
        List<Map<String, Object>> children = groupedByResaux.entrySet().stream()
                .map(entry -> {
                    String resauxName = entry.getKey();

                    // Group by Division under each Resaux
                    Map<String, List<Adressip>> groupedByDivision = entry.getValue().stream()
                            .filter(a -> a.getDivision() != null) // Ensure Division is not null
                            .collect(Collectors.groupingBy(a -> a.getDivision().getDesignation()));

                    // Create Division Nodes
                    List<Map<String, Object>> divisionNodes = groupedByDivision.entrySet().stream()
                            .map(divEntry -> {
                                Map<String, Object> divisionNode = new HashMap<>();
                                divisionNode.put("name", divEntry.getKey()); // Division name
                                divisionNode.put("children", divEntry.getValue().stream()
                                        .map(a -> Map.of("name", a.getIp())) // Adressip nodes
                                        .collect(Collectors.toList()));
                                return divisionNode;
                            }).collect(Collectors.toList());

                    // Create Resaux Node
                    Map<String, Object> resauxNode = new HashMap<>();
                    resauxNode.put("name", resauxName);
                    resauxNode.put("children", divisionNodes);
                    return resauxNode;
                }).collect(Collectors.toList());

        // Final Tree Structure
        return Map.of("name", organeName, "children", children);
    }

}

