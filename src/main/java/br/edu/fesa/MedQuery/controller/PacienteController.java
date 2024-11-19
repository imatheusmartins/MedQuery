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
@RequestMapping("paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping("/cadastro-paciente")
    public ModelAndView cadastro(Paciente paciente){
        ModelAndView mv =  new ModelAndView("login/login");

        String hashSenha = PasswordUtil.encoder(paciente.getSenha());
        paciente.setSenha(hashSenha);

        mv.addObject("paciente", paciente); //usuario é o objeto enviado pelo html.

        try {
        pacienteRepository.save(paciente);
        System.out.println("Salvo com sucesso: " + paciente.getNome() + " " + paciente.getImagem());
        return home();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }

    }

    @GetMapping("/inicio")
    public ModelAndView home(){
        ModelAndView mv =  new ModelAndView("login/login");
        return mv;
    }

}
