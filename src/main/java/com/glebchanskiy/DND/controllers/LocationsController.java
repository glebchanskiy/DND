package com.glebchanskiy.DND.controllers;

import com.glebchanskiy.DND.models.Location;
import com.glebchanskiy.DND.services.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/locations")
public class LocationsController {

    private final LocationsService locationsService;

    @Autowired
    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping()
    public String index(@RequestParam(name = "search", required = false) String search,
                        Model model) {
        // Возвращает страничку со всеми локациями (или содержащими в названии определенную строку)
        if (search!=null) {
            model.addAttribute("locations", locationsService.findAllByNameContaining(search));
        } else {
            model.addAttribute("locations", locationsService.findAll());
        }

        return "locations/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // Информация по локации
        model.addAttribute("location", locationsService.findOne(id));
        return "locations/show";
    }

    @GetMapping("/new")
    public String newLocationPage(@ModelAttribute("location") Location location) {
        return "locations/new";
    }

    @PostMapping()
    public String saveLocation(@ModelAttribute("location") Location location) {
        locationsService.save(location);
        return "redirect:/locations";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("location", locationsService.findOne(id));
        return "locations/edit";
    }

    @PatchMapping("/{id}")
    public String updateLocation(@PathVariable("id") int id,
                                 @ModelAttribute("location") Location location) {
        locationsService.update(id, location);
        return "redirect:/locations";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        locationsService.delete(id);
        return "redirect:/locations";
    }

}
