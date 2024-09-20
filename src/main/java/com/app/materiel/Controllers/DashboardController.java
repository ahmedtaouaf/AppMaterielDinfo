package com.app.materiel.Controllers;

import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Repository.StockRepository;
import com.app.materiel.Service.MouvementService;
import com.app.materiel.Service.StockService;
import com.app.materiel.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private MouvementService mouvementService;
    @Autowired
    private StockService stockService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String dashboardpage(Model model){

        List<Mouvement> lastSixMouvements = mouvementService.findLastSixMouvements();
        model.addAttribute("mouvements", lastSixMouvements);
        model.addAttribute("totalStock", stockService.totalStock());
        model.addAttribute("totalType", typeService.totalType());
        model.addAttribute("totalMouvements", mouvementService.totalMvn());

        return "index";
    }

    @GetMapping("/stock/type-counts")
    @ResponseBody
    public List<Map<String, Object>> getTypeCounts() {
        List<Object[]> results = stockRepository.countStocksByType();
        List<Map<String, Object>> data = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", result[1]);
            map.put("name", result[0]);
            data.add(map);
        }

        return data;
    }



}
