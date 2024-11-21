package br.edu.fesa.MedQuery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fesa.MedQuery.model.Paciente;
import br.edu.fesa.MedQuery.repositories.PacienteRepository;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
public class UserController {

    @Autowired
    private PacienteRepository pacienteRepository;


    @GetMapping("/login")
    public ModelAndView login(Paciente paciente){
        ModelAndView mv =  new ModelAndView("login/login");

        mv.addObject("paciente", paciente);

        return mv;
    } 

    @PostMapping("/cadastro")
    public String cadastro(Paciente paciente){
        ModelAndView mv =  new ModelAndView("login/login");

        String hashSenha = PasswordUtil.encoder(paciente.getSenha());
        paciente.setSenha(hashSenha);

        mv.addObject("paciente", paciente); //usuario é o objeto enviado pelo html.

        try {
            pacienteRepository.save(paciente);
            System.out.println("Salvo com sucesso: " + paciente.getEmail());
            return "redirect:/login";
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return "redirect:/login";
        }
    }
}
