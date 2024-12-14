package com.app.materiel.Controllers;

import com.app.materiel.Service.OrganeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class OrganeController {

    private final OrganeService organeService;

    public OrganeController(OrganeService organeService) {
        this.organeService = organeService;
    }


    @GetMapping("/api/organe-tree")
    @ResponseBody
    public Map<String, Object> getOrganeTree(@RequestParam String organeName) {
        return organeService.getOrganeTree(organeName);
    }

    @GetMapping("/organe/adresses")
    public String showOrganeTree() {


        return "organe-tree";
    }
}
