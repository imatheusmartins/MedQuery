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
import br.edu.fesa.MedQuery.model.Gestor;
import br.edu.fesa.MedQuery.model.Paciente;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.GestorRepository;
import br.edu.fesa.MedQuery.repositories.PacienteRepository;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorRepository gestorRepository;
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping("/cadastro")
    public ModelAndView cadastro(Gestor gestor){
        ModelAndView mv =  new ModelAndView("login/login");

        String hashSenha = PasswordUtil.encoder(gestor.getSenha());
        gestor.setSenha(hashSenha);

        mv.addObject("gestor", gestor); //usuario é o objeto enviado pelo html.

        try {
            gestorRepository.save(gestor);
            System.out.println("Salvo com sucesso: " + gestor.getEmail());
        return home();
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
        }
    }

    @GetMapping("list-gestores")
    public ModelAndView gestoresList(){
        ModelAndView mv = new ModelAndView("gestor/gestor-list");
        mv.addObject("gestores", gestorRepository.findAll());
        return mv;
    }

    @GetMapping("/home-gestor")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv =  new ModelAndView("home/index");
        Pageable pageReq = PageRequest.of((page - 1),  2);
        Page<Agendamento> resultPage = agendamentoRepository.findAll(pageReq);
        mv.addObject("agendamentosList", resultPage);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("gestor/editar");
        mv.addObject("gestor", gestorRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-gestor")
    public ModelAndView editar(Gestor gestor){
        ModelAndView mv =  new ModelAndView("gestor/editar");
        gestorRepository.save(gestor);
        return gestoresList();
    }

    @GetMapping("/login")
    public ModelAndView home(){
        ModelAndView mv =  new ModelAndView("login/login");
        return mv;
    }
}
