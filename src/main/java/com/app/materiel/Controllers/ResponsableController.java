package com.app.materiel.Controllers;

import com.app.materiel.Entity.Responsable;
import com.app.materiel.Service.ResponsableService;
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
public class ResponsableController {

    @Autowired
    private ResponsableService responsableService;




    @GetMapping("/responsable/new")
    public String pagenewresponsable(Model model){

        model.addAttribute("responsable", new Responsable());
        return "responsable-new";
    }

    @GetMapping("/responsable/list")
    public String pagelistresponsable(Model model){

        List<Responsable> responsables = responsableService.findResponsable();
        model.addAttribute("responsables", responsables);

        return "responsable-list";
    }

    @PostMapping("/addresponsable")
    public String addResponsable(@ModelAttribute Responsable responsable, RedirectAttributes redirectAttributes) {

        responsableService.addResponsable(responsable);
        redirectAttributes.addFlashAttribute("responsableajouter", "Responsable est bien ajouter avec succès !");
        return "redirect:/responsable/new";
    }

}
