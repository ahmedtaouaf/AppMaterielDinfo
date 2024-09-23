package com.app.materiel.Controllers;

import com.app.materiel.Entity.Position;
import com.app.materiel.Entity.Responsable;
import com.app.materiel.Service.PositionService;
import com.app.materiel.Service.ResponsableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PositionController {

    @Autowired
    private PositionService positionService;


    @GetMapping("/position/list")
    public String pagelistresponsable(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);

        List<Position> positions = positionService.findPosition();
        model.addAttribute("positions", positions);
        model.addAttribute("position", new Position());
        return "position-list";
    }

    @PostMapping("/addposition")
    public String addPosition(@ModelAttribute Position position, RedirectAttributes redirectAttributes) {

        positionService.addPosition(position);
        redirectAttributes.addFlashAttribute("positionajouter", "Position est bien ajouter avec succès !");
        return "redirect:/position/list";
    }
}
