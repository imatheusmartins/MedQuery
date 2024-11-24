package br.edu.fesa.MedQuery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Medico;
import br.edu.fesa.MedQuery.model.Paciente;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.MedicoRepository;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping("/cadastro")
    public ModelAndView cadastro(Medico medico){
        ModelAndView mv =  new ModelAndView("login/login");

        String hashSenha = PasswordUtil.encoder(medico.getSenha());
        medico.setSenha(hashSenha);

        mv.addObject("medico", medico); //usuario é o objeto enviado pelo html.

        try {
            medicoRepository.save(medico);
            System.out.println("Salvo com sucesso: " + medico.getEmail());
        return home();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }
    }

    @GetMapping("list-medicos")
    public ModelAndView medicosList(){
        ModelAndView mv = new ModelAndView("medico/medico-list");
        mv.addObject("medicos", medicoRepository.findAll());
        return mv;
    }

    @GetMapping("/home-medico")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv =  new ModelAndView("home/index");
        Pageable pageReq = PageRequest.of((page - 1),  2);
        Page<Agendamento> resultPage = agendamentoRepository.findAll(pageReq);
        mv.addObject("agendamentosList", resultPage);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("medico/editar");
        mv.addObject("medico", medicoRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-medico")
    public ModelAndView editar(Medico medico){
        ModelAndView mv =  new ModelAndView("medico/editar");
        medicoRepository.save(medico);
        return medicosList();
    }

    @GetMapping("/login")
    public ModelAndView home(){
        ModelAndView mv =  new ModelAndView("login/login");
        return mv;
    }
}
