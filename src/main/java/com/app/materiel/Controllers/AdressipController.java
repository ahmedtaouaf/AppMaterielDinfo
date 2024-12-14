package com.app.materiel.Controllers;

import com.app.materiel.Entity.*;
import com.app.materiel.Repository.AdressipRepository;
import com.app.materiel.Service.AdressipService;
import com.app.materiel.Service.DivisionService;
import com.app.materiel.Service.OrganeService;
import com.app.materiel.Service.ResauxService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdressipController {

    private final AdressipService adressipService;
    private final OrganeService organeService;

    private final ResauxService resauxService;
    private final DivisionService divisionService;

    public AdressipController(AdressipService adressipService, OrganeService organeService, ResauxService resauxService, DivisionService divisionService, AdressipRepository adressipRepository) {
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

        if (!isValidIPAddress(adressip.getIp())) {
            redirectAttributes.addFlashAttribute("Adresseipformat", "Format d'adresse ip incorrecte");
            return "redirect:/adressage/create";
        }

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

    @GetMapping("/adressage/liste/{resaux}")
    public String listIPAddressesByResaux(
            @PathVariable("resaux") String resaux,
            Model model,
            @RequestParam(value = "search", required = false) String searchTerm,
            @RequestParam(value = "organe", required = false) Long organeId,
            @RequestParam(value = "page", defaultValue = "0") int page) {

        Page<Adressip> adressipsPage = adressipService.findAdressesByResauxAndOrgane(resaux, searchTerm, organeId, page);
        List<Organe> organes = organeService.findAll();

        model.addAttribute("adressPage", adressipsPage);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("resaux", resaux);
        model.addAttribute("organes", organes);
        model.addAttribute("selectedOrgane", organeId);
        return "adresse-list";
    }




    @PostMapping("/delete/{id}")
    public String deleteAdressip(@PathVariable Long id, RedirectAttributes redirectAttributes) {

            adressipService.deleteAdressipById(id);
            redirectAttributes.addFlashAttribute("deleteMessage", "Adresse supprimée avec succès.");

        return "redirect:/adressage/liste";
    }

    @GetMapping("/adressip/edit/{id}")
    public String showEditAdressIpForm(@PathVariable Long id, Model model) {
        Adressip adressip = adressipService.getAdressIpById(id);

        model.addAttribute("adressip", adressip);
        model.addAttribute("organes", organeService.findAll());
        model.addAttribute("divisions", divisionService.findAll());
        model.addAttribute("resaux", resauxService.findAll());
        return "adressip-edit";
    }

    @PostMapping("/adressip/edit")
    public String updateAdressIpAttributes(@ModelAttribute Adressip adressip, RedirectAttributes redirectAttributes) {

        Adressip existingadressip = adressipService.getAdressIpById(adressip.getId());

        existingadressip.setIp(adressip.getIp());
        existingadressip.setDesignation(adressip.getDesignation());
        existingadressip.setMac(adressip.getMac());
        existingadressip.setResponsable(adressip.getResponsable());
        existingadressip.setService(adressip.getService());
        existingadressip.setMateriel(adressip.getMateriel());
        existingadressip.setTemporaire(adressip.getTemporaire());
        existingadressip.setDivision(adressip.getDivision());
        existingadressip.setResaux(adressip.getResaux());
        existingadressip.setOrgane(adressip.getOrgane());
        adressipService.save(existingadressip);

        String resau = existingadressip.getResaux().getNom();

        redirectAttributes.addFlashAttribute("editMessage", "Adresse IP Modifier Avec Succés !");
        return "redirect:/adressage/liste/" + resau;
    }


}

