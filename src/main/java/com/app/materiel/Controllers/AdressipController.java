package com.app.materiel.Controllers;

import com.app.materiel.Entity.Adressip;
import com.app.materiel.Entity.Organe;
import com.app.materiel.Entity.Stock;
import com.app.materiel.Service.AdressipService;
import com.app.materiel.Service.DivisionService;
import com.app.materiel.Service.OrganeService;
import com.app.materiel.Service.ResauxService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "adresse-new";
    }

    @PostMapping("/create")
    public String createAdressip(@ModelAttribute("adressip") Adressip adressip,
                                 @RequestParam("organe") Long organeId,
                                 RedirectAttributes redirectAttributes) {

        if (adressipService.existsByAdressip(adressip.getIp())) {
            redirectAttributes.addFlashAttribute("Adresseipexist", "Adresse IP existe déja");

            return "redirect:/adressage/create";
        }

        Organe organe = organeService.findById(organeId);
        adressip.setOrgane(organe);

        adressipService.save(adressip);
        redirectAttributes.addFlashAttribute("Adresseipadd", "Adresse IP créée avec succès.");

        return "redirect:/adressage/create";
    }

    private boolean isValidIPAddress(String ipAddress) {
        String ipPattern =
                "^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$";
        return ipAddress != null && ipAddress.matches(ipPattern);
    }

    @GetMapping("/adressage/liste")
    public String listIPAddresses(Model model,
                                  @RequestParam(value = "search", required = false) String searchTerm,
                                  @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Adressip> adressipsPage;
        adressipsPage = adressipService.findAllAdresses(searchTerm, page);

        model.addAttribute("adressPage", adressipsPage);
        model.addAttribute("searchTerm", searchTerm);
        return "adresse-list";
    }
}

