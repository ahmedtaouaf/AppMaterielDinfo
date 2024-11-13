package com.app.materiel.Controllers;

import com.app.materiel.Entity.Serveur;
import com.app.materiel.Entity.VirtualMachine;
import com.app.materiel.Service.ServeurService;
import com.app.materiel.Service.VirtualMachineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ServeurController {

    @Autowired
    private ServeurService serveurService;

    @Autowired
    private VirtualMachineService virtualMachineService;


    @GetMapping("/serveurs/{resaux}")
    public String showServers(@PathVariable("resaux") String resaux, Model model) {

        Iterable<Serveur> serveurs = serveurService.getServersByResaux(resaux);
        model.addAttribute("serveurs", serveurs);
        model.addAttribute("resaux", resaux);
        return "serveurs";
    }

    @GetMapping("/serveurs/details/{id}")
    public String showServerDetails(@PathVariable("id") Long id, Model model) {
        Serveur serveur = serveurService.getServerById(id);
        List<VirtualMachine> virtualMachines = virtualMachineService.getVirtualMachinesByServeur(serveur);

        model.addAttribute("serveur", serveur);
        model.addAttribute("virtualMachines", virtualMachines);
        return "serveur-details";
    }

    @GetMapping("/tree")
    public String showTree(Model model) {
        List<Serveur> serveurs = serveurService.getAllServers();
        String treeData = buildTreeData(serveurs);
        model.addAttribute("treeData", treeData);
        return "tree-view";
    }



    @Autowired
    private ObjectMapper objectMapper;

    public String buildTreeData(List<Serveur> serveurs) {
        List<Map<String, Object>> treeData = new ArrayList<>();
        for (Serveur serveur : serveurs) {
            Map<String, Object> serverNode = new HashMap<>();
            serverNode.put("name", serveur.getNom());

            List<Map<String, Object>> vmNodes = new ArrayList<>();
            for (VirtualMachine vm : serveur.getVirtualMachines()) {
                Map<String, Object> vmNode = new HashMap<>();
                vmNode.put("name", vm.getDesignation());

                Map<String, Object> logicielNode = new HashMap<>();
                logicielNode.put("name", vm.getLogiciel().getNom());

                vmNode.put("children", List.of(logicielNode));
                vmNodes.add(vmNode);
            }
            serverNode.put("children", vmNodes);
            treeData.add(serverNode);
        }

        try {
            return objectMapper.writeValueAsString(treeData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }

}
