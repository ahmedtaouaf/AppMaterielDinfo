package com.app.materiel.Controllers;

import com.app.materiel.Entity.Serveur;
import com.app.materiel.Entity.VirtualMachine;
import com.app.materiel.Service.ServeurService;
import com.app.materiel.Service.VirtualMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VirtualMachineController {

    @Autowired
    private ServeurService serveurService;

    @Autowired
    private VirtualMachineService virtualMachineService;

    @GetMapping("/virtual-machines/add")
    public String showAddVirtualMachineForm(Model model) {
        model.addAttribute("virtualMachine", new VirtualMachine());
        model.addAttribute("logiciels", virtualMachineService.getAllLogiciels());
        model.addAttribute("serveurs", serveurService.getServeursByResaux("intranet")); // Default to intranet
        return "vm-add";
    }

    @GetMapping("/serveurs/byResaux")
    @ResponseBody
    public List<Serveur> getServeursByResaux(@RequestParam("resaux") String resaux) {
        return serveurService.getServeursByResaux(resaux);
    }

    @PostMapping("/virtual-machines/add")
    public String saveVirtualMachine(@ModelAttribute VirtualMachine virtualMachine) {
        virtualMachineService.saveVirtualMachine(virtualMachine);
        return "redirect:/virtual-machines/add";
    }
}
