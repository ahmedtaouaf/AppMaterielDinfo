package com.app.materiel.Controllers;

import com.app.materiel.Entity.Adressip;
import com.app.materiel.Entity.Organe;
import com.app.materiel.Entity.Resaux;
import com.app.materiel.Entity.Stock;
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

    @PostMapping("/delete/{id}")
    public String deleteAdressip(@PathVariable Long id, RedirectAttributes redirectAttributes) {

            adressipService.deleteAdressipById(id);
            redirectAttributes.addFlashAttribute("deleteMessage", "Adresse supprimée avec succès.");

        return "redirect:/adressage/liste";
    }

    @GetMapping("/adressip-filter")
    public String getAdressipFilterPage(Model model) {
        List<Resaux> resauxList = resauxService.findAll();
        List<Organe> organeList = organeService.findAll();

        model.addAttribute("resauxList", resauxList);
        model.addAttribute("organeList", organeList);

        return "adressip-filter";
    }

    // This method will handle AJAX requests for filtering with pagination
    @GetMapping("/filter-adresses")
    @ResponseBody
    public Page<Adressip> filterAdresses(@RequestParam(value = "resaux", required = false) String resaux,
                                         @RequestParam(value = "organe", required = false) Long organe,
                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "5") int size) {

        // Filter the Adressips based on selected filters and paginate the result
        if (resaux != null && !resaux.isEmpty() && organe != null) {
            return adressipService.filterAdresses(resaux, organe, PageRequest.of(page, size));
        } else if (resaux != null && !resaux.isEmpty()) {
            return adressipService.filterAdressesByResaux(resaux, PageRequest.of(page, size));
        } else if (organe != null) {
            return adressipService.filterAdressesByOrgane(organe, PageRequest.of(page, size));
        } else {
            return adressipService.getAllAdresses(PageRequest.of(page, size));
        }
    }

}

