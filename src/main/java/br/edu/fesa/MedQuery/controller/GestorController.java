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
import br.edu.fesa.MedQuery.model.Gestor;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.GestorRepository;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorRepository gestorRepository;
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;


    //Método que incializa o html
    @GetMapping("/cadastro")
    public ModelAndView getCadastroGestor(Gestor gestor){
        ModelAndView mv =  new ModelAndView("gestor/cadastro");
        UserRole[] userRoleGestor = {UserRole.GESTOR};
        mv.addObject("userRoles", userRoleGestor);
        mv.addObject("gestor", gestor);

        return mv;
    }

    //Método que recebe o dado enviado no form do html 
    @PostMapping("/cadastro-gestor")
    public String postCadastroGestor(@ModelAttribute Gestor gestor){
        ModelAndView mv =  new ModelAndView("gestor/cadastro");

        String hashSenha = PasswordUtil.encoder(gestor.getSenha());
        gestor.setSenha(hashSenha);

        mv.addObject("gestor", gestor);

        try {
            gestorRepository.save(gestor);
            System.out.println("Salvo com sucesso: " + gestor.getEmail());
            return "redirect:/home";
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return "redirect:/home";
        }
    }

    @GetMapping("list-gestores")
    public ModelAndView gestoresList(){
        ModelAndView mv = new ModelAndView("gestor/lista");
        mv.addObject("gestores", gestorRepository.findAll());
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("gestor/editar");
        mv.addObject("userRoles", UserRole.values());
        mv.addObject("gestor", gestorRepository.findById(id));
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id){
        gestorRepository.deleteById(id);
        return "redirect:/gestor/list-gestores";
    }
    
    
    @PostMapping("/editar-gestor")
    public String editar(Gestor gestor){
        ModelAndView mv =  new ModelAndView("gestor/editar");
        gestorRepository.save(gestor);
        return "redirect:/gestor/list-gestores";
    }
    
    //Não utilizado
    @GetMapping("/home-gestor")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv =  new ModelAndView("home/index");
        Pageable pageReq = PageRequest.of((page - 1),  2);
        Page<Agendamento> resultPage = agendamentoRepository.findAll(pageReq);
        mv.addObject("agendamentosList", resultPage);
        return mv;
    }
}
