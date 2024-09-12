package com.app.materiel.Controllers;

import com.app.materiel.Service.StatusService;
import com.app.materiel.Service.StockService;
import com.app.materiel.Service.TypeService;
import com.app.materiel.Dto.StockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private StatusService statusService;

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

}

