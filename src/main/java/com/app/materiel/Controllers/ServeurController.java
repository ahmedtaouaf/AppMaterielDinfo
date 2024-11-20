package com.app.materiel.Controllers;

import com.app.materiel.Entity.Serveur;
import com.app.materiel.Entity.VirtualMachine;
import com.app.materiel.Repository.VirtualMachineRepository;
import com.app.materiel.Service.ServeurService;
import com.app.materiel.Service.VirtualMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ServeurController {

    @Autowired
    private ServeurService serveurService;

    @Autowired
    private VirtualMachineService virtualMachineService;
    @Autowired
    private VirtualMachineRepository virtualMachineRepository;


    @GetMapping("/serveurs/{resaux}")
    public String showServers(@PathVariable("resaux") String resaux, Model model) {

        Iterable<Serveur> serveurs = serveurService.getServersByResaux(resaux);
        model.addAttribute("serveurs", serveurs);
        model.addAttribute("resaux", resaux);
        return "serveurs";
    }

    @GetMapping("/serveurs/details/{id}")
    public String showServerDetails(@PathVariable("id") Long id, Model model) {
        Serveur serveur = serveurService.getServerById(id);
        List<VirtualMachine> virtualMachines = virtualMachineService.getVirtualMachinesByServeur(serveur);

        model.addAttribute("serveur", serveur);
        model.addAttribute("virtualMachines", virtualMachines);
        return "serveur-details";
    }

    @GetMapping("/serveur/add")
    public String addServer(Model model) {

        model.addAttribute("serveur", new Serveur());
        return "serveur-add";
    }

    @PostMapping("/addserveur")
    public String saveServer(@ModelAttribute("serveur") Serveur serveur) {
        serveurService.saveServer(serveur);
        return "redirect:/serveur/add";
    }
    @GetMapping("/server/edit/{id}")
    public String showEditServerForm(@PathVariable Long id, Model model) {
        Serveur serveur = serveurService.getServeurById(id);
        model.addAttribute("serveur", serveur);
        return "serveur-edit";
    }

    @PostMapping("/server/edit")
    public String updateServerAttributes(@ModelAttribute Serveur serveur, RedirectAttributes redirectAttributes) {

        Serveur existingServeur = serveurService.getServeurById(serveur.getId());

        existingServeur.setNom(serveur.getNom());
        existingServeur.setAdresseip(serveur.getAdresseip());
        existingServeur.setCpu(serveur.getCpu());
        existingServeur.setRam(serveur.getRam());
        existingServeur.setHyperviseur(serveur.getHyperviseur());
        existingServeur.setNserie(serveur.getNserie());
        existingServeur.setResaux(serveur.getResaux());


        serveurService.saveServer(existingServeur);

        String serveurResau = existingServeur.getResaux();

        redirectAttributes.addFlashAttribute("editMessage", "Serveur Modifier Avec Succés !");
        return "redirect:/serveurs/"+serveurResau;
    }

    @GetMapping("/server/delete/{id}")
    public String deleteServer(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        Serveur serveur = serveurService.getServerById(id);

        virtualMachineRepository.deleteByServeurId(serveur.getId());

        String serveurResau = serveur.getResaux();

        serveurService.deleteServeurById(id);

        redirectAttributes.addFlashAttribute("deleteMessage", "Serveur Supprimé Avec Succés !");
        return "redirect:/serveurs/"+serveurResau;
    }

    @Value("${materiel.schemas}")
    String svgDirectoryPath;

    @GetMapping("/schema/liste")
    public String getSvgCatalogue(Model model) {

        //String svgDirectoryPath = "C:\\svgs";
        File folder = new File(svgDirectoryPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".svg"));

        List<String> svgPaths = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                svgPaths.add("/svgs/" + file.getName());
            }
        }

        model.addAttribute("svgPaths", svgPaths);
        return "schemasresaux";
    }





}
