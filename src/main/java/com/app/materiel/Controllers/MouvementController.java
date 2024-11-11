package com.app.materiel.Controllers;

import com.app.materiel.Dto.MouvementDto;
import com.app.materiel.Entity.*;
import com.app.materiel.Repository.*;
import com.app.materiel.Service.MouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
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
    private TypeRepository typeRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ResponsableRepository responsableRepository;

    @GetMapping("/mouvements/new")
    public String showMouvementForm(Model model) {


        List<Status> allStatuses = statusRepository.findAll();
        List<Status> filteredStatuses = allStatuses.stream()
                .filter(status -> !status.getLibelle().equals("DISPONIBLE") &&
                        !status.getLibelle().equals("INDISPONIBLE") &&
                        !status.getLibelle().equals("ENTREE AU STOCK"))
                .collect(Collectors.toList());

        List<Type> types = typeRepository.findAll();

        List<Responsable> responsables= responsableRepository.findAll();

        model.addAttribute("statuses", filteredStatuses);
        model.addAttribute("types", types);
        model.addAttribute("responsables", responsables);
        model.addAttribute("mouvement", new MouvementDto());
        model.addAttribute("positions", positionRepository.findAll());

        return "mouvement-new";
    }


    @PostMapping("/addMouvement")
    public String addMouvement(@ModelAttribute("mouvement") MouvementDto mouvementDto, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("successMessage", "Mouvement Est Très Bien Enregistrer !");
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
        redirectAttributes.addFlashAttribute("successMessage", "Mouvement Est Très Bien Enregistrer !");
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

    @GetMapping("/stocks")
    @ResponseBody
    public List<Stock> getStocksByTypeAndDisponibleStatus(@RequestParam Long typeId) {
        String statusDisponible = "DISPONIBLE";
        return stockRepository.findByTypeIdAndStatusLibelle(typeId, statusDisponible);
    }

    @GetMapping("/mouvement/search")
    public String showSearchForm(Model model) {

        return "mouvement-search";
    }

    @PostMapping("/mouvement/search")
    public String searchMouvements(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            Model model) {

        List<Mouvement> mouvements = mouvementService.findMouvementsByDateRange(startDate, endDate);
        model.addAttribute("mouvements", mouvements);
        return "mouvement-search";
    }

}
