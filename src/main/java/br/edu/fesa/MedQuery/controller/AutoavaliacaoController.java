package br.edu.fesa.MedQuery.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fesa.MedQuery.model.Paciente;
import br.edu.fesa.MedQuery.repositories.PacienteRepository;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
@RequestMapping("/autoavaliacao")
public class AutoavaliacaoController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public ModelAndView autoavaliacao(){
        ModelAndView mv =  new ModelAndView("agendamento/autoavaliacao");
        return mv;
    }

}
