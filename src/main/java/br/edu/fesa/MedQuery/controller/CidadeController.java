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
import br.edu.fesa.MedQuery.model.Cidade;
import br.edu.fesa.MedQuery.repositories.CidadeRepository;

public class CidadeController {

    private CidadeRepository cidadeRepository;

    public CidadeController(CidadeRepository cidadeRepository){
        this.cidadeRepository = cidadeRepository;
    }

    // @GetMapping
    // public ModelAndView clinicaHome(@RequestParam(defaultValue = "1") int page){
    //     ModelAndView mv = new ModelAndView("home/index");
    //     Page pageReq = PageRequest.of((page - 1),  2);
    //     Page<Agendamento> resultPage = chamadoRepository.findAll(pageReq);
    //     mv.addObject("chamadosList", resultPage);
    //     return mv;
    // }

    @GetMapping("/home-cidade")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv =  new ModelAndView("home/index");
        Pageable pageReq = PageRequest.of((page - 1),  2);
        Page<Cidade> resultPage = cidadeRepository.findAllCidades(pageReq);
        mv.addObject("cidadesList", resultPage);
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView getCadastro(Cidade cidade){
        ModelAndView mv = new ModelAndView("cidade/cadastro");
        mv.addObject("cidade", cidade);
        UserRole[] profiles = {UserRole.ADMIN, UserRole.GESTOR};
        mv.addObject("userRoles", profiles);
        return mv;
    }

    @PostMapping("/cadastro-cidade")
    public ModelAndView cadastro(@ModelAttribute Cidade cidade){
       ModelAndView mv =  new ModelAndView("cidade/cadastro");
       mv.addObject("cidade", cidade);

       try {
        cidadeRepository.save(cidade);
        System.out.println("Salvo com sucesso: " + cidade.getNome());
        return home(1);
       } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
       }
    }

    @GetMapping("list-cidade")
    public ModelAndView cidadesList(){
        ModelAndView mv = new ModelAndView("cidade/cidade-list");
        mv.addObject("cidades", cidadeRepository.findAll());
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("cidade/editar");
        mv.addObject("cidade", cidadeRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-cidade")
    public ModelAndView editar(Cidade cidade){
        ModelAndView mv =  new ModelAndView("cidade/editar");
        cidadeRepository.save(cidade);
        return cidadesList();
    }
}
