package br.edu.fesa.MedQuery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "/home"; // Retorna home.html localizado em src/main/resources/templates
    }
}
