package com.app.materiel.Controllers;

import com.app.materiel.Entity.Serveur;
import com.app.materiel.Entity.VirtualMachine;
import com.app.materiel.Service.ServeurService;
import com.app.materiel.Service.VirtualMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/virtual-machines/edit/{id}")
    public String showEditVirtualMachineForm(@PathVariable Long id, Model model) {
        VirtualMachine virtualMachine = virtualMachineService.getVirtualMachineById(id);
        model.addAttribute("virtualMachine", virtualMachine);
        model.addAttribute("logiciels", virtualMachineService.getAllLogiciels());
        return "vm-edit";
    }

    @PostMapping("/virtual-machines/edit")
    public String updateVirtualMachineAttributes(@ModelAttribute VirtualMachine virtualMachine, RedirectAttributes redirectAttributes) {

        VirtualMachine existingVm = virtualMachineService.getVirtualMachineById(virtualMachine.getId());

        existingVm.setDesignation(virtualMachine.getDesignation());
        existingVm.setAdresseip(virtualMachine.getAdresseip());
        existingVm.setCpu(virtualMachine.getCpu());
        existingVm.setRam(virtualMachine.getRam());
        existingVm.setStockage(virtualMachine.getStockage());
        existingVm.setOs(virtualMachine.getOs());
        existingVm.setType(virtualMachine.getType());
        existingVm.setLogiciel(virtualMachine.getLogiciel());

        virtualMachineService.saveVirtualMachine(existingVm);

        Long serveurId = existingVm.getServeur().getId();

        redirectAttributes.addFlashAttribute("editMessage", "Virtuelle Machine Modifier Avec Succés !");
        return "redirect:/serveurs/details/" + serveurId;
    }


    @GetMapping("/virtual-machines/delete/{id}")
    public String deleteVirtualMachine(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        virtualMachineService.deleteVirtualMachineById(id);

        redirectAttributes.addFlashAttribute("deleteMessage", "Virtuelle Machine Supprimée Avec Succés !");
        return "redirect:/serveurs/details/{id}";
    }

}
