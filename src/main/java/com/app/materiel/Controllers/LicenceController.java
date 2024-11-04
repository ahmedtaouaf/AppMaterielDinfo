package com.app.materiel.Controllers;

import com.app.materiel.Entity.Licence;
import com.app.materiel.Entity.Responsable;
import com.app.materiel.Service.LicenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LicenceController {

    @Autowired
    private LicenceService licenceService;

    @GetMapping("/licence/new")
    public String pageNewLicence(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        model.addAttribute("licence", new Licence());

        return "licence-new";
    }

    @GetMapping("/licence/liste")
    public String pageListLicence(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Licence> licences= licenceService.findLicence();
        model.addAttribute("username", username);
        model.addAttribute("licences", licences);
        return "licence-list";
    }

    @PostMapping("/addlicence")
    public String addlicence(@ModelAttribute Licence licence, RedirectAttributes redirectAttributes) {

        licenceService.addLicence(licence);
        redirectAttributes.addFlashAttribute("licenceajouter", "Licence est bien ajouter avec succès !");
        return "redirect:/licence/new";
    }
}
