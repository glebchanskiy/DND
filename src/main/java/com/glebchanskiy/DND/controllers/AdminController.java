package com.glebchanskiy.DND.controllers;


import com.glebchanskiy.DND.services.LocationsService;
import com.glebchanskiy.DND.services.PlantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    // контроллер для admin page
    private final PlantsService plantsService;
    private final LocationsService locationsService;

    @Autowired
    public AdminController(PlantsService plantsService, LocationsService locationsService) {
        this.plantsService = plantsService;
        this.locationsService = locationsService;
    }

    @GetMapping
    public String index(Model model) {
        // страница со всеми таблицами
        model.addAttribute("plants", plantsService.findAll());
        model.addAttribute("locations", locationsService.findAll());
        return "admin/info";
    }
}
