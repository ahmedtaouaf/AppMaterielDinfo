package com.app.materiel.Controllers;

import com.app.materiel.Entity.Licence;
import com.app.materiel.Service.LicenceService;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
}

