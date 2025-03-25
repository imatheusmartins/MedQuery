package br.edu.fesa.MedQuery.controller;

import java.security.Principal;
import java.util.List;

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

import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Paciente;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.PacienteRepository;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
public class HomeController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping("/home")
    public ModelAndView login(Paciente paciente){
        ModelAndView mv =  new ModelAndView("home/home");

        List<Agendamento> lstAgendamento = agendamentoRepository.findAllWithClinicaAndMedico();

        mv.addObject("agendamentos", lstAgendamento);
        return mv;
    } 
}
