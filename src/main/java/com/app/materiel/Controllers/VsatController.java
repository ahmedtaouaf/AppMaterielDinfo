package com.app.materiel.Controllers;

import com.app.materiel.Entity.*;
import com.app.materiel.Repository.HistoriquePanneRepository;
import com.app.materiel.Service.ArticleVsatService;
import com.app.materiel.Service.HistoriquePanneService;
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
    private final ArticleVsatService articleVsatService;
    private final HistoriquePanneService historiquePanneService;

    @Autowired
    public VsatController(UniteRespService uniteRespService, PosteService posteService, ArticleVsatService articleVsatService, HistoriquePanneService historiquePanneService) {
        this.uniteRespService = uniteRespService;
        this.posteService = posteService;
        this.articleVsatService = articleVsatService;
        this.historiquePanneService = historiquePanneService;
    }

    @GetMapping("/vsat")
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
        Poste poste = posteService.getPosteById(posteId);
        model.addAttribute("poste", poste);
        model.addAttribute("articles", poste.getArticles());
        return "poste-details";
    }


    @GetMapping("/article/{articleId}/signal")
    public String showSignalPage(@PathVariable Long articleId, Model model) {
        ArticleVsat article = articleVsatService.getArticleById(articleId);
        model.addAttribute("article", article);
        model.addAttribute("historiqueEtats", HistoriqueEtat.values());
        return "signal-issue";
    }


    @PostMapping("/article/{articleId}/signal")
    public String handleSignalIssue(@PathVariable Long articleId,
                                    @RequestParam String cause,
                                    @RequestParam String observation,
                                    @RequestParam HistoriqueEtat historiqueEtat) {
        articleVsatService.updateArticleStatus(articleId, historiqueEtat, cause, observation);

        ArticleVsat article = articleVsatService.getArticleById(articleId);
        Long posteId = article.getPoste().getId();

        return "redirect:/map/poste/" + posteId;
    }

    @GetMapping("/article/{articleId}/history")
    public String showArticleHistory(@PathVariable Long articleId, Model model) {

        ArticleVsat article = articleVsatService.getArticleById(articleId);

        List<HistoriquePanne> history = historiquePanneService.getHistoryByArticleId(articleId);

        model.addAttribute("article", article);
        model.addAttribute("history", history);

        return "article-history";
    }



}
