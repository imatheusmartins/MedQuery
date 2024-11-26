package br.edu.fesa.MedQuery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fesa.MedQuery.enums.UserRole;
import br.edu.fesa.MedQuery.model.Especialidade;
import br.edu.fesa.MedQuery.repositories.EspecialidadeRepository;

public class EspecialidadeController {

    private EspecialidadeRepository especialidadeRepository;

    // public EspecialidadeController(EspecialidadeRepository especialidadeRepository){
    //     this.especialidadeRepository = especialidadeRepository;
    // }

    // // @GetMapping
    // // public ModelAndView clinicaHome(@RequestParam(defaultValue = "1") int page){
    // //     ModelAndView mv = new ModelAndView("home/index");
    // //     Page pageReq = PageRequest.of((page - 1),  2);
    // //     Page<Agendamento> resultPage = chamadoRepository.findAll(pageReq);
    // //     mv.addObject("chamadosList", resultPage);
    // //     return mv;
    // // }

    // @GetMapping("/home-especialidade")
    // public ModelAndView home(@RequestParam(defaultValue = "1") int page){
    //     ModelAndView mv =  new ModelAndView("home/index");
    //     Pageable pageReq = PageRequest.of((page - 1),  2);
    //     Page<Especialidade> resultPage = especialidadeRepository.findAllEspecialidades(pageReq);
    //     mv.addObject("especialidadesList", resultPage);
    //     return mv;
    // }

    // @GetMapping("/cadastro")
    // public ModelAndView getCadastro(Especialidade especialidade){
    //     ModelAndView mv = new ModelAndView("especialidade/cadastro");
    //     mv.addObject("especialidade", especialidade);
    //     UserRole[] profiles = {UserRole.ADMIN, UserRole.GESTOR};
    //     mv.addObject("userRoles", profiles);
    //     return mv;
    // }

    // @PostMapping("/cadastro-especialidade")
    // public ModelAndView cadastro(@ModelAttribute Especialidade especialidade){
    //    ModelAndView mv =  new ModelAndView("especialidade/cadastro");
    //    mv.addObject("especialidade", especialidade);

    //    try {
    //     especialidadeRepository.save(especialidade);
    //     System.out.println("Salvo com sucesso: " + especialidade.getNome());
    //     return home(1);
    //    } catch (Exception e) {
    //         mv.addObject("msgErro", e.getMessage());
    //         System.out.println("Erro ao salvar " + e.getMessage());
    //         return mv;
    //    }
    // }

    // @GetMapping("list-especialidades")
    // public ModelAndView especialidadesList(){
    //     ModelAndView mv = new ModelAndView("especialidade/especialidade-list");
    //     mv.addObject("especialidades", especialidadeRepository.findAll());
    //     return mv;
    // }

    // @GetMapping("/editar/{id}")
    // public ModelAndView editar(@PathVariable("id") Integer id){
    //     ModelAndView mv =  new ModelAndView("especialidade/editar");
    //     mv.addObject("especialidade", especialidadeRepository.findById(id));
    //     return mv;
    // }

    // @PostMapping("/editar-especialidade")
    // public ModelAndView editar(Especialidade especialidade){
    //     ModelAndView mv =  new ModelAndView("especialidade/editar");
    //     especialidadeRepository.save(especialidade);
    //     return especialidadesList();
    // }
}
