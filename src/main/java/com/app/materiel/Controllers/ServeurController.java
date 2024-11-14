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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/serveur/add")
    public String addServer(Model model) {

        model.addAttribute("serveur", new Serveur());
        return "serveur-add";
    }

    @PostMapping("/addserveur")
    public String saveServer(@ModelAttribute("serveur") Serveur serveur) {
        serveurService.saveServer(serveur);
        return "redirect:/serveur/add";
    }





}
