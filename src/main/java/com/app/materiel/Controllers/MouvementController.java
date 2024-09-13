package com.app.materiel.Controllers;

import com.app.materiel.Dto.MouvementDto;
import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Entity.Status;
import com.app.materiel.Entity.Stock;
import com.app.materiel.Repository.PositionRepository;
import com.app.materiel.Repository.StatusRepository;
import com.app.materiel.Repository.StockRepository;
import com.app.materiel.Service.MouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MouvementController {

    @Autowired
    private MouvementService mouvementService;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping("/mouvements/new")
    public String showMouvementForm(Model model) {
        model.addAttribute("mouvement", new MouvementDto());
        model.addAttribute("stocks", stockRepository.findByStatusLibelle("Disponible"));
        model.addAttribute("positions", positionRepository.findAll());

        List<Status> allStatuses = statusRepository.findAll();
        List<Status> filteredStatuses = allStatuses.stream()
                .filter(status -> !status.getLibelle().equals("DISPONIBLE") &&
                        !status.getLibelle().equals("INDISPONIBLE"))
                .collect(Collectors.toList());
        model.addAttribute("statuses", filteredStatuses);

        return "mouvement-new";
    }

    @PostMapping("/addMouvement")
    public String addMouvement(@ModelAttribute("mouvement") MouvementDto mouvementDto, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("successMessage", "Mouvement est très bien enregistrer !");
        mouvementService.saveMouvement(mouvementDto);
        return "redirect:/mouvements/new";
    }

    @GetMapping("/mouvements/liste")
    public String listStocks(
            @RequestParam(value = "search", required = false) String searchTerm,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        Page<Mouvement> stockPage = mouvementService.findAllmouvements(searchTerm, page);

        model.addAttribute("stockPage", stockPage);
        model.addAttribute("searchTerm", searchTerm);

        return "mouvements-list";
    }

    @GetMapping("/mouvements/entree")
    public String showEntreeMouvementForm(Model model) {
        model.addAttribute("mouvement", new MouvementDto());
        model.addAttribute("stocks", stockRepository.findByStatusLibelle("Indisponible"));
        model.addAttribute("statuses", statusRepository.findAll());
        return "mouvement-entree";
    }

    @PostMapping("/addEntreeMouvement")
    public String addEntreeMouvement(@ModelAttribute("mouvement") MouvementDto mouvementDto, RedirectAttributes redirectAttributes) {
        mouvementService.saveEntreeMouvement(mouvementDto);
        return "redirect:/mouvements/entree";
    }

    @GetMapping("/mouvements/listeentree")
    public String listStocksentree(
            @RequestParam(value = "search", required = false) String searchTerm,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        Page<Mouvement> stockPage = mouvementService.findAllmouvementsEntree(searchTerm, page);

        model.addAttribute("stockPage", stockPage);
        model.addAttribute("searchTerm", searchTerm);

        return "mouvement-listentree";
    }

}
