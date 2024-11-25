package br.edu.fesa.MedQuery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.ClinicaRepository;
import br.edu.fesa.MedQuery.repositories.GestorRepository;
import br.edu.fesa.MedQuery.repositories.MedicoRepository;

@Controller
@RequestMapping("/clinica")
public class ClinicaController {

    private ClinicaRepository clinicaRepository;
    private AgendamentoRepository agendamentoRepository;
    private MedicoRepository medicoRepository;
    private GestorRepository gestorRepository;

    public ClinicaController(ClinicaRepository clinicaRepository, AgendamentoRepository agendamentoRepository, MedicoRepository medicoRepository, GestorRepository gestorRepository){
        this.clinicaRepository = clinicaRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.medicoRepository = medicoRepository;
        this.gestorRepository = gestorRepository;

    }

    // @GetMapping
    // public ModelAndView clinicaHome(@RequestParam(defaultValue = "1") int page){
    //     ModelAndView mv = new ModelAndView("home/index");
    //     Page pageReq = PageRequest.of((page - 1),  2);
    //     Page<Agendamento> resultPage = chamadoRepository.findAll(pageReq);
    //     mv.addObject("chamadosList", resultPage);
    //     return mv;
    // }

    @GetMapping("/cadastro")
    public ModelAndView getCadastro(Clinica clinica){
        ModelAndView mv = new ModelAndView("clinica/cadastro");
        mv.addObject("clinica", clinica);
        UserRole[] profiles = {UserRole.ADMIN, UserRole.GESTOR};
        mv.addObject("userRoles", profiles);
        return mv;
    }

    // @PostMapping("/cadastro-clinica")
    // public ModelAndView cadastro(@ModelAttribute Clinica clinica, @RequestParam("file") MultipartFile imagem){
    //    ModelAndView mv =  new ModelAndView("tecnico/cadastro");
    // //    String hashSenha = PasswordUtil.encoder(clinica.getSenha());
    // //    clinica.setSenha(hashSenha);
    //    mv.addObject("tecnico", tecnico);

    //    try {
    //     if(UploadUtil.fazerUploadImagem(imagem)){
    //         tecnico.setImagem(imagem.getOriginalFilename());
    //     }
    //     tecRepository.save(tecnico);
    //     System.out.println("Salvo com sucesso: " + tecnico.getNome() + " " + tecnico.getImagem());
    //     return home(1);
    //    } catch (Exception e) {
    //         mv.addObject("msgErro", e.getMessage());
    //         System.out.println("Erro ao salvar " + e.getMessage());
    //         return mv;
    //    }
    // }

    @PostMapping("/cadastro-clinica")
    public ModelAndView cadastro(@ModelAttribute Clinica clinica){
       ModelAndView mv =  new ModelAndView("clinica/cadastro");
    //    String hashSenha = PasswordUtil.encoder(clinica.getSenha());
    //    clinica.setSenha(hashSenha);
       mv.addObject("clinica", clinica);

       try {
        clinicaRepository.save(clinica);
        System.out.println("Salvo com sucesso: " + clinica.getNome() + " " + clinica.getImagem());
        return home(1);
       } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return mv;
       }
    }


    @GetMapping("list-clinicas")
    public ModelAndView clinicasList(){
        ModelAndView mv = new ModelAndView("clinica/clinica-list");
        mv.addObject("clinicas", clinicaRepository.findAll());
        return mv;
    }

    @GetMapping("/home-clinica")
    public ModelAndView home(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv =  new ModelAndView("home/index");
         PageRequest pageReq = PageRequest.of((page - 1),  2);
        Page<Agendamento> resultPage = agendamentoRepository.findAllAgendamentos(pageReq);
        mv.addObject("agendamentosList", resultPage);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("clinica/editar");
        mv.addObject("clinica", clinicaRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-clinica")
    public ModelAndView editar(Clinica clinica){
        ModelAndView mv =  new ModelAndView("clinica/editar");
        clinicaRepository.save(clinica);
        return clinicasList();
    }
}
