package com.app.materiel.Controllers;

import com.app.materiel.Entity.Licence;
import com.app.materiel.Entity.Mouvement;
import com.app.materiel.Repository.MouvementRepository;
import com.app.materiel.Repository.StockRepository;
import com.app.materiel.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private LicenceService licenceService;
    @Autowired
    private PosteService posteService;
    @Autowired
    private MouvementRepository mouvementRepository;

    @GetMapping("/")
    public String dashboardpage(Model model){



        List<Object[]> mouvementData = mouvementRepository.countMouvementsByType();

        List<String> labels = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        for (Object[] row : mouvementData) {
            labels.add((String) row[0]);        // Type name
            values.add(((Long) row[1]).intValue());  // Count of mouvements
        }

        List<Map<String, Object>> radarIndicators = new ArrayList<>();
        for (String label : labels) {
            Map<String, Object> indicator = new HashMap<>();
            indicator.put("name", label);
            indicator.put("max", 100);
            radarIndicators.add(indicator);
        }

        model.addAttribute("radarIndicators", radarIndicators);
        model.addAttribute("values", values);


        List<Object[]> mouvementsPerDay = mouvementRepository.countMouvementByDay();

        List<String> days = new ArrayList<>();
        List<Long> counts = new ArrayList<>();

        for (Object[] row : mouvementsPerDay) {
            days.add(row[0].toString());
            counts.add((Long) row[1]);
        }

        List<Mouvement> lastSixMouvements = mouvementService.findLastSixMouvements();
        List<Licence> licences = licenceService.findLicence();

        model.addAttribute("days", days);
        model.addAttribute("mouvementCounts", counts);
        model.addAttribute("mouvements", lastSixMouvements);
        model.addAttribute("totalStock", stockService.totalStock());
        model.addAttribute("totalPoste", posteService.totalPoste());
        model.addAttribute("totalType", typeService.totalType());
        model.addAttribute("totalMouvements", mouvementService.totalMvn());
        model.addAttribute("totalLicences", licenceService.totalLicence());
        model.addAttribute("totalExpiree", licenceService.totalExpiree());
        model.addAttribute("totalPresque", licenceService.totalPresque());
        model.addAttribute("licences", licences);


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

    private String formatExpirationDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
        return sdf.format(date);
    }



}
