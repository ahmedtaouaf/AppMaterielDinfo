package com.app.materiel.Controllers;

import com.app.materiel.Entity.Licence;
import com.app.materiel.Entity.Responsable;
import com.app.materiel.Entity.Situation;
import com.app.materiel.Repository.SituationRepository;
import com.app.materiel.Service.LicenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class LicenceController {

    @Autowired
    private LicenceService licenceService;
    @Autowired
    private SituationRepository situationRepository;

    @GetMapping("/licence/liste")
    public String pageListLicence(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Licence> licences = licenceService.findLicence();
        updateLicencesSituation(licences);

        for (Licence licence : licences) {
            licence.calculateProgress();
        }

        model.addAttribute("username", username);
        model.addAttribute("licences", licences);
        return "licence-list";
    }



    private void updateLicencesSituation(List<Licence> licences) {
        LocalDate now = LocalDate.now();

        Situation expired = situationRepository.findByNom("EXPIREE")
                .orElseThrow(() -> new RuntimeException("Situation 'EXPIREE' not found"));
        Situation almostExpired = situationRepository.findByNom("PRESQUE")
                .orElseThrow(() -> new RuntimeException("Situation 'PRESQUE' not found"));
        Situation valid = situationRepository.findByNom("EN COURS")
                .orElseThrow(() -> new RuntimeException("Situation 'EN COURS' not found"));

        for (Licence licence : licences) {
            LocalDate expirationDate = licence.getDateexpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysBetween = ChronoUnit.DAYS.between(now, expirationDate);

            if (daysBetween < 0) {
                licence.setSituation(expired);
            } else if (daysBetween <= 30) {
                licence.setSituation(almostExpired);
            } else {
                licence.setSituation(valid);
            }
        }
    }

    @GetMapping("/licence/new")
    public String pageNewLicence(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        model.addAttribute("licence", new Licence());

        return "licence-new";
    }

    @PostMapping("/licence/save")
    public String saveLicence(@ModelAttribute("licence") Licence licence, RedirectAttributes redirectAttributes) {

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, 1);
        Date oneMonthBeforeExpiry = calendar.getTime();

        Situation situation;
        if (licence.getDateexpiration().before(currentDate)) {
            situation = situationRepository.findByNom("EXPIREE")
                    .orElseThrow(() -> new RuntimeException("Situation 'EXPIREE' not found"));
        } else if (licence.getDateexpiration().before(oneMonthBeforeExpiry)) {
            situation = situationRepository.findByNom("PRESQUE")
                    .orElseThrow(() -> new RuntimeException("Situation 'PRESQUE' not found"));
        } else {
            situation = situationRepository.findByNom("EN COURS")
                    .orElseThrow(() -> new RuntimeException("Situation 'EN COURS' not found"));
        }
        licence.setSituation(situation);

        redirectAttributes.addFlashAttribute("successMessage", "Licence Est Très Bien Enregistrer !");
        licenceService.saveLicence(licence);
        return "redirect:/licence/liste";
    }

    @GetMapping("/licence/edit/{id}")
    public String editLicence(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Licence licence = licenceService.findById(id);
        model.addAttribute("username", username);
        model.addAttribute("licence", licence);
        return "licence-new";
    }

}
