package com.app.materiel.Controllers;

import com.app.materiel.Entity.Licence;
import com.app.materiel.Service.LicenceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalAdviceController {

    private final LicenceService licenceService;

    @Autowired
    public GlobalAdviceController(LicenceService licenceService) {
        this.licenceService = licenceService;
    }

    @ModelAttribute("notifications")
    public List<Licence> addNotificationsToModel() {
        return licenceService.findExpiringLicenses();
    }

    @ModelAttribute("connecteduser")
    public String getconnecteduser(){

        Authentication authent = SecurityContextHolder.getContext().getAuthentication();
        String username = authent.getName();

        return username;
    }
}

