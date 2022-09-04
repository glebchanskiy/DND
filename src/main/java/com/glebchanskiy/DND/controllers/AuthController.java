package com.glebchanskiy.DND.controllers;

import com.glebchanskiy.DND.models.Person;
import com.glebchanskiy.DND.services.RegistrationService;
import com.glebchanskiy.DND.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    // Контроллер для авторизации на сайте
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") Person person, BindingResult br) {
        personValidator.validate(person, br);

        if (br.hasErrors())
            return "/auth/registration";

        registrationService.register(person);

        return "redirect:/auth/login";
    }
}
