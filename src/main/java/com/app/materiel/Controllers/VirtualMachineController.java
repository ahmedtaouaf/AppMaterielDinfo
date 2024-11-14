package com.app.materiel.Controllers;

import com.app.materiel.Entity.Serveur;
import com.app.materiel.Entity.VirtualMachine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VirtualMachineController {

    @GetMapping("/virtualmachine/new")
    public String addVms(Model model) {

        model.addAttribute("virtualmachine", new VirtualMachine());
        return "vm-add";
    }
}
