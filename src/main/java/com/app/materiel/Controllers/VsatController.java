package com.app.materiel.Controllers;

import com.app.materiel.Entity.Poste;
import com.app.materiel.Entity.UniteResp;
import com.app.materiel.Service.PosteService;
import com.app.materiel.Service.UniteRespService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/map")
public class VsatController {

    private final UniteRespService uniteRespService;
    private final PosteService posteService;

    @Autowired
    public VsatController(UniteRespService uniteRespService, PosteService posteService) {
        this.uniteRespService = uniteRespService;
        this.posteService = posteService;
    }

    @GetMapping
    public String showMap(Model model) {
        List<UniteResp> unites = uniteRespService.getAllUniteResp();
        model.addAttribute("unites", unites);
        return "vsat-map";
    }

    @GetMapping("/postes/{uniteId}")
    @ResponseBody
    public List<Poste> getPostesByUnite(@PathVariable Long uniteId) {
        return posteService.getPostesByUniteResp(uniteId);
    }

    @GetMapping("/poste/{posteId}")
    public String showPosteDetails(@PathVariable Long posteId, Model model) {
        Poste poste = posteService.getPosteById(posteId); // Fetch Poste by ID
        model.addAttribute("poste", poste);
        model.addAttribute("articles", poste.getArticles());
        return "poste-details"; // Thymeleaf template for Poste details
    }

}
