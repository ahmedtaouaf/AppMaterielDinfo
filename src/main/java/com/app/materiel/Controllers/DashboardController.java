package com.app.materiel.Controllers;

import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Repository.MouvementRepository;
import com.app.materiel.Repository.StockRepository;
import com.app.materiel.Service.MouvementService;
import com.app.materiel.Service.StockService;
import com.app.materiel.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private MouvementRepository mouvementRepository;

    @GetMapping("/")
    public String dashboardpage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        // Fetch the count of mouvements by type
        List<Object[]> mouvementData = mouvementRepository.countMouvementsByType();

        // Prepare the labels and values for the radar chart
        List<String> labels = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        for (Object[] row : mouvementData) {
            labels.add((String) row[0]);        // Type name
            values.add(((Long) row[1]).intValue());  // Count of mouvements
        }

        // Create radar indicators in JSON-like format
        List<Map<String, Object>> radarIndicators = new ArrayList<>();
        for (String label : labels) {
            Map<String, Object> indicator = new HashMap<>();
            indicator.put("name", label);
            indicator.put("max", 100);  // Adjust 'max' based on your data
            radarIndicators.add(indicator);
        }

        // Add the data to the model
        model.addAttribute("radarIndicators", radarIndicators);
        model.addAttribute("values", values);


        // Fetch mouvements per day from your repository
        List<Object[]> mouvementsPerDay = mouvementRepository.countMouvementByDay();

        // Prepare data for chart
        List<String> days = new ArrayList<>();
        List<Long> counts = new ArrayList<>();

        for (Object[] row : mouvementsPerDay) {
            days.add(row[0].toString()); // Day name (e.g., Mon, Tue, etc.)
            counts.add((Long) row[1]);   // Count of mouvements on that day
        }

        model.addAttribute("days", days);
        model.addAttribute("mouvementCounts", counts);

        List<Mouvement> lastSixMouvements = mouvementService.findLastSixMouvements();
        model.addAttribute("mouvements", lastSixMouvements);
        model.addAttribute("totalStock", stockService.totalStock());
        model.addAttribute("totalType", typeService.totalType());
        model.addAttribute("totalMouvements", mouvementService.totalMvn());
        model.addAttribute("username", username);

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
