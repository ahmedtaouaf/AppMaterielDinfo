package com.app.materiel.Controllers;

import com.app.materiel.Dto.TypeSummaryDto;
import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Entity.Stock;
import com.app.materiel.Service.MouvementService;
import com.app.materiel.Service.StatusService;
import com.app.materiel.Service.StockService;
import com.app.materiel.Service.TypeService;
import com.app.materiel.Dto.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private MouvementService mouvementService;

    @GetMapping("/article/new")
    public String showAddStockForm(Model model) {
        model.addAttribute("stock", new StockDto());
        model.addAttribute("types", typeService.findtypes());
        model.addAttribute("statuses", statusService.findstatus());
        return "article-new";
    }

    @PostMapping("/addStock")
    public String addStock(@ModelAttribute("stock") StockDto stockDto, RedirectAttributes redirectAttributes) {

        stockService.saveStock(stockDto);


        redirectAttributes.addFlashAttribute("successMessage", "Article ajouté au stock avec succès !");

        return "redirect:/article/new";
    }


    @GetMapping("/article/liste")
    public String listStocks(
            @RequestParam(value = "search", required = false) String searchTerm,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        Page<Stock> stockPage = stockService.findAllStocks(searchTerm, page);

        model.addAttribute("stockPage", stockPage);
        model.addAttribute("searchTerm", searchTerm);

        return "article-list";
    }

    @GetMapping("/global-stock-etat")
    public String getGlobalStockEtat(Model model) {
        List<TypeSummaryDto> typeSummary = stockService.getTypeSummary();
        model.addAttribute("typeSummary", typeSummary);
        return "global-stock-etat";
    }

    @GetMapping("/article/{id}/mouvements")
    public String listMouvements(@PathVariable("id") Long stockId,
                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                 @RequestParam(value = "size", defaultValue = "5") int size,
                                 Model model) {

        Stock stock = stockService.findStockById(stockId);
        Page<Mouvement> mouvementPage = mouvementService.findMovementsByStockOrderByDateDesc(stock, page, size);

        model.addAttribute("mouvementPage", mouvementPage);
        model.addAttribute("stock", stock);

        return "mouvement-history";
    }








}

