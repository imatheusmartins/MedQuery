package br.edu.fesa.MedQuery.controller;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Paciente;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.PacienteRepository;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
@RequestMapping("/pacienteteste")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping("/cadastroteste")
    public ModelAndView cadastro(Paciente paciente){
        ModelAndView mv =  new ModelAndView("login/login");

        String hashSenha = PasswordUtil.encoder(paciente.getSenha());
        paciente.setSenha(hashSenha);

        mv.addObject("paciente", paciente); //usuario é o objeto enviado pelo html.

        try {
            pacienteRepository.save(paciente);
            System.out.println("Salvo com sucesso: " + paciente.getEmail());
        return home();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }
    }

    @GetMapping("list-pacientes")
    public ModelAndView pacientesList(){
        ModelAndView mv = new ModelAndView("paciente/paciente-list");
        mv.addObject("pacientes", pacienteRepository.findAll());
        return mv;
    }

    @GetMapping("/home-paciente")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv =  new ModelAndView("home/index");
         Pageable pageReq = PageRequest.of((page - 1),  2);
        Page<Agendamento> resultPage = agendamentoRepository.findAll(pageReq);
        mv.addObject("agendamentosList", resultPage);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("paciente/editar");
        mv.addObject("paciente", pacienteRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-paciente")
    public ModelAndView editar(Paciente paciente){
        ModelAndView mv =  new ModelAndView("paciente/editar");
        pacienteRepository.save(paciente);
        return pacientesList();
    }

    @GetMapping("/login")
    public ModelAndView home(){
        ModelAndView mv =  new ModelAndView("login/login");
        return mv;
    }

}
