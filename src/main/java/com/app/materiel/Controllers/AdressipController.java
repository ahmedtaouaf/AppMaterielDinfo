package com.app.materiel.Controllers;

import com.app.materiel.Entity.Adressip;
import com.app.materiel.Service.AdressipService;
import com.app.materiel.Service.DivisionService;
import com.app.materiel.Service.OrganeService;
import com.app.materiel.Service.ResauxService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdressipController {

    private final AdressipService adressipService;
    private final OrganeService organeService;

    private final ResauxService resauxService;
    private final DivisionService divisionService;

    public AdressipController(AdressipService adressipService, OrganeService organeService, ResauxService resauxService, DivisionService divisionService) {
        this.adressipService = adressipService;
        this.organeService = organeService;
        this.resauxService = resauxService;
        this.divisionService = divisionService;
    }

    @GetMapping("/adressage/create")
    public String showCreateForm(Model model) {
        model.addAttribute("adressip", new Adressip());
        model.addAttribute("organes", organeService.findAll());
        model.addAttribute("resaux", resauxService.findAll());
        model.addAttribute("divisions", divisionService.findAll());
        return "create";
    }

    @PostMapping("/create")
    public String createAdressip(@ModelAttribute("adressip") @Validated Adressip adressip,
                                 BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (adressipService.existsByAdressip(adressip.getAdressip())) {
            result.rejectValue("adressip", "error.adressip", "This IP address is already taken.");
            redirectAttributes.addAttribute("Adressexist","This IP address is already taken.");
        }

        if (result.hasErrors()) {
            model.addAttribute("organes", organeService.findAll());
            model.addAttribute("resaux", resauxService.findAll());
            model.addAttribute("divisions", divisionService.findAll());
            return "/create";
        }

        adressipService.save(adressip);
        return "redirect:/adressage/create";
    }
    @GetMapping("/list")
    public String listIPAddresses(Model model) {
        model.addAttribute("addresses", adressipService.findall());
        return "list";
    }
}

