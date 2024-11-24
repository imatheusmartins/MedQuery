package br.edu.fesa.MedQuery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fesa.MedQuery.enums.UserRole;
import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.repositories.ClinicaRepository;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
@RequestMapping("/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaRepository clinicaRepository;

    // @Autowired
    // private ChamadoRepository chamadoRepository;

    @GetMapping
    public ModelAndView clinicaHome(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv = new ModelAndView("home/index");
        Pageable pageReq = PageRequest.of((page - 1),  2);
        Page<Chamado> resultPage = chamadoRepository.findAll(pageReq);
        mv.addObject("chamadosList", resultPage);
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(Clinica clinica){
        ModelAndView mv = new ModelAndView("clinica/cadastro");
        mv.addObject("clinica", clinica);
        UserRole[] profiles = {UserRole.ADMIN, UserRole.GESTOR};
        mv.addObject("perfils", profiles);
        return mv;
    }

    @PostMapping("/cadastro-clinica")
    public ModelAndView cadastro(@ModelAttribute Clinica clinica, @RequestParam("file") MultipartFile imagem){
       ModelAndView mv =  new ModelAndView("tecnico/cadastro");
    //    String hashSenha = PasswordUtil.encoder(clinica.getSenha());
    //    clinica.setSenha(hashSenha);
       mv.addObject("tecnico", tecnico);

       try {
        if(UploadUtil.fazerUploadImagem(imagem)){
            tecnico.setImagem(imagem.getOriginalFilename());
        }
        tecRepository.save(tecnico);
        System.out.println("Salvo com sucesso: " + tecnico.getNome() + " " + tecnico.getImagem());
        return home(1);
       } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
       }

    }


    @GetMapping("list-tecnicos")
    public ModelAndView tecnicosList(){
        ModelAndView mv = new ModelAndView("tecnico/tecnico-list");
        mv.addObject("tecnicos", tecRepository.findAll());
        return mv;
    }

    @GetMapping("/home-tecnico")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv =  new ModelAndView("home/index");
         Pageable pageReq = PageRequest.of((page - 1),  2);
        Page<Chamado> resultPage = chamadoRepository.findAll(pageReq);
        mv.addObject("chamadosList", resultPage);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("tecnico/editar");
        mv.addObject("perfils", Perfil.values());
        mv.addObject("tec", tecRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-tecnico")
    public ModelAndView editar(Tecnico tecnico){
        ModelAndView mv =  new ModelAndView("tecnico/editar");
        tecRepository.save(tecnico);
        return tecnicosList();
    }
}
