package br.edu.fesa.MedQuery.controller;

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
import org.springframework.web.servlet.ModelAndView;

import br.edu.fesa.MedQuery.enums.UserRole;
import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Medico;
import br.edu.fesa.MedQuery.model.Paciente;
import br.edu.fesa.MedQuery.model.User;
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


    //Método que incializa o html
    @GetMapping("/cadastro")
    public ModelAndView getCadastroMedico(Medico medico){
        ModelAndView mv =  new ModelAndView("medico/cadastro");
        UserRole[] userRoleMedico = {UserRole.MEDICO};
        mv.addObject("userRoles", userRoleMedico);
        mv.addObject("medico", medico);

        return mv;
    }

    //Método que recebe o dado enviado no form do html 
    @PostMapping("/cadastro-medico")
    public String postCadastroMedico(@ModelAttribute Medico medico){
        ModelAndView mv =  new ModelAndView("medico/cadastro");

        String hashSenha = PasswordUtil.encoder(medico.getSenha());
        medico.setSenha(hashSenha);

        mv.addObject("medico", medico);

        try {
            medicoRepository.save(medico);
            System.out.println("Salvo com sucesso: " + medico.getEmail());
            return "redirect:/home";
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return "redirect:/home";
        }
    }

    @GetMapping("list-medicos")
    public ModelAndView medicosList(){
        ModelAndView mv = new ModelAndView("medico/lista");
        mv.addObject("medicos", medicoRepository.findAll());
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("medico/editar");
        mv.addObject("userRoles", UserRole.values());
        mv.addObject("medico", medicoRepository.findById(id));
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id){
        medicoRepository.deleteById(id);
        return "redirect:/medico/list-medicos";
    }
    
    
    @PostMapping("/editar-medico")
    public String editar(Medico medico){
        ModelAndView mv =  new ModelAndView("medico/editar");
        medicoRepository.save(medico);
        return "redirect:/medico/list-medicos";
    }
    
    //Não utilizado
    @GetMapping("/home-medico")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv =  new ModelAndView("home/index");
        Pageable pageReq = PageRequest.of((page - 1),  2);
        Page<Agendamento> resultPage = agendamentoRepository.findAll(pageReq);
        mv.addObject("agendamentosList", resultPage);
        return mv;
    }
}
