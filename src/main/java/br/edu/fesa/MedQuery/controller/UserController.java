package br.edu.fesa.MedQuery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fesa.MedQuery.enums.UserRole;
import br.edu.fesa.MedQuery.model.Paciente;
import br.edu.fesa.MedQuery.model.User;
import br.edu.fesa.MedQuery.repositories.GestorRepository;
import br.edu.fesa.MedQuery.repositories.MedicoRepository;
import br.edu.fesa.MedQuery.repositories.PacienteRepository;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
public class UserController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private GestorRepository gestorRepository;

    @Autowired
    private MedicoRepository medicoRepository;


    @GetMapping("/login")
    public ModelAndView login(User user){
        ModelAndView mv =  new ModelAndView("login/login");

        mv.addObject("user", user);

        return mv;
    } 

    @GetMapping("/cadastro")
    public ModelAndView cadastro(Paciente paciente){
        ModelAndView mv =  new ModelAndView("login/cadastro");
        mv.addObject("userRoles", UserRole.values());
        mv.addObject("paciente", paciente);

        return mv;
    }

    @PostMapping("/cadastro-user")
    public String cadastroOld(Paciente paciente){
        ModelAndView mv =  new ModelAndView("login/cadastro");

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
