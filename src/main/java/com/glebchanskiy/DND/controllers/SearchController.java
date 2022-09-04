package com.glebchanskiy.DND.controllers;


import com.glebchanskiy.DND.models.Location;
import com.glebchanskiy.DND.services.LocationsService;
import com.glebchanskiy.DND.services.PlantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final PlantsService plantsService;
    private final LocationsService locationsService;

    @Autowired
    public SearchController(PlantsService plantsService, LocationsService locationsService) {
        this.plantsService = plantsService;
        this.locationsService = locationsService;
    }

    @GetMapping()
    public String search(Model model) {
        model.addAttribute("locations", locationsService.findAll());
        return "search/searching_form";
    }

    @GetMapping("/index")
    public String index(//@ModelAttribute("location_id") int location_id,
                        //@ModelAttribute("rareness") String rareness,
                        //@ModelAttribute("count") int count,
                        @RequestParam("location_id") int location_id,
                        @RequestParam("rareness") String rareness,
                        @RequestParam("count") int count,
                        Model model) {
        model.addAttribute("plants",
                plantsService.findRandomPlants(
                        locationsService.findOne(location_id),
                        rareness,
                        count));
        return "search/index";
    }
}