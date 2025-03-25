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

import br.edu.fesa.MedQuery.enums.Cidade;
import br.edu.fesa.MedQuery.enums.UserRole;
import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.model.Medico;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.ClinicaRepository;

@Controller
@RequestMapping("/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;
    
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    //Método que incializa o html
    @GetMapping("/cadastro")
    public ModelAndView getCadastroClinica(Clinica clinica){
        ModelAndView mv =  new ModelAndView("clinica/cadastro");
        mv.addObject("cidades", Cidade.values());
        mv.addObject("clinica", clinica);

        return mv;
    }

    //Método que recebe o dado enviado no form do html 
    @PostMapping("/cadastro-clinica")
    public String postCadastroClinica(@ModelAttribute Clinica clinica){
        ModelAndView mv =  new ModelAndView("clinica/cadastro");;

        mv.addObject("clinica", clinica);

        try {
            clinicaRepository.save(clinica);
            System.out.println("Salvo com sucesso: " + clinica.getNome());
            return "redirect:/home";
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return "redirect:/home";
        }
    }

    @GetMapping("list-clinica")
    public ModelAndView clinicasList(){
        ModelAndView mv = new ModelAndView("clinica/lista");
        mv.addObject("clinicas", clinicaRepository.findAll());
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("clinica/editar");
        mv.addObject("cidades", Cidade.values());
        mv.addObject("clinica", clinicaRepository.findById(id));
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Integer id){
        clinicaRepository.deleteById(id);
        return "redirect:/clinica/list-clinica";
    }
    
    
    @PostMapping("/editar-clinica")
    public String editar(Clinica clinica){
        ModelAndView mv =  new ModelAndView("clinica/editar");
        clinicaRepository.save(clinica);
        return "redirect:/clinica/list-clinica";
    }
    
    //Não utilizado
    @GetMapping("/home-clinica")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv =  new ModelAndView("home/index");
        Pageable pageReq = PageRequest.of((page - 1),  2);
        Page<Agendamento> resultPage = agendamentoRepository.findAll(pageReq);
        mv.addObject("agendamentosList", resultPage);
        return mv;
    }
}
