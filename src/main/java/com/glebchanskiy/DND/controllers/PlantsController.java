package com.glebchanskiy.DND.controllers;



//import jakarta.validation.Valid;
import com.glebchanskiy.DND.models.Plant;
import com.glebchanskiy.DND.services.LocationsService;
import com.glebchanskiy.DND.services.PlantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/plants")
public class PlantsController {

    private final PlantsService plantsService;
    private final LocationsService locationsService;

    @Autowired
    public PlantsController(PlantsService plantsService, LocationsService locationsService) {
        this.plantsService = plantsService;
        this.locationsService = locationsService;
    }

    @GetMapping()
    public String index(@RequestParam(name = "search", required = false) String search,
                        Model model) {
        if (search!=null)
            model.addAttribute("plants", plantsService.findAllByNameContaining(search));
        else
            model.addAttribute("plants", plantsService.findAll());
        return "plants/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("plant", plantsService.findOne(id));
        return "plants/show";
    }

    @GetMapping("/new")
    public String newPlantPage(@ModelAttribute("plant") Plant plant,
                           Model model) {
        model.addAttribute("AllLocations", locationsService.findAll());
        return "plants/new";
    }

    @PostMapping()
    public String savePlant(@ModelAttribute("plant") Plant plant,
                           BindingResult bindingResult
                           ) {
        if(bindingResult.hasErrors()) {
            return "plants/new";
        }

        plantsService.save(plant);
        return "redirect:/plants";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("plant", plantsService.findOne(id));
        model.addAttribute("AllLocations", locationsService.findAll());
        return "plants/edit";
    }

    @PatchMapping("/{id}")
    public String updatePlant(@PathVariable("id") int id,
                         @ModelAttribute("plant")  Plant plant,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "plants/edit";

        plantsService.update(id, plant);
        return "redirect:/plants";
    }

    @DeleteMapping("/{id}")
    public String deletePlant(@PathVariable("id") int id) {
        plantsService.delete(id);
        return "redirect:/plants";
    }
}
