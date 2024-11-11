package br.edu.fesa.MedQuery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class LoginController {
    
    @GetMapping("/index")
    public String getLoginPage(){
        return "/index";
    }
}
