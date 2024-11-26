package br.edu.fesa.MedQuery.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fesa.MedQuery.model.Especialidade;
import br.edu.fesa.MedQuery.enums.EspecialidadeEnum;
import br.edu.fesa.MedQuery.enums.Intensidade;
import br.edu.fesa.MedQuery.enums.Status;
import br.edu.fesa.MedQuery.enums.TipoServico;
import br.edu.fesa.MedQuery.enums.UserRole;
import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.model.Medico;
import br.edu.fesa.MedQuery.model.Sintoma;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.MedicoRepository;
import br.edu.fesa.MedQuery.repositories.SintomaRepository;
import br.edu.fesa.MedQuery.service.AgendamentoService;
import br.edu.fesa.MedQuery.service.MedicoService;
import br.edu.fesa.MedQuery.util.Autoavaliacao;

@Controller
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final MedicoRepository medicoRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final SintomaRepository sintomaRepository;

    private final AgendamentoService agendamentoService;
    private MedicoService medicoService;

    public AgendamentoController(MedicoRepository medicoRepository, AgendamentoRepository agendamentoRepository, AgendamentoService agendamentoService, 
    MedicoService medicoService, SintomaRepository sintomaRepository) {
        this.medicoRepository = medicoRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoService = agendamentoService;
        this.medicoService = medicoService;
        this.sintomaRepository = sintomaRepository;
    }

    //Método que incializa o html
    @GetMapping("/cadastro")
    public ModelAndView getCadastroMedico(Medico medico){
        ModelAndView mv =  new ModelAndView("medico/cadastro");
        mv.addObject("medico", medico);

        return mv;
    }

    @GetMapping("/agendamento-servico")
    public ModelAndView criar(Agendamento agendamento){
        ModelAndView mv = new ModelAndView("agendamento/criar");

        // Especialidade especialidadeSelecionada = new Especialidade("teste");

        // List<Medico> medicos = medicoRepository.findByEspecialidadeId(especialidadeSelecionada.getId());

        // if(medicos == null)
        //     medicos = new ArrayList<>();

        mv.addObject("tipoServicos", TipoServico.values());
        mv.addObject("agendamento", agendamento);
        return mv;
    }

    @GetMapping("/agendamento-redireciona")
    public ModelAndView redireciona(Agendamento agendamento){
        
        if(agendamento.getTipoServico() == TipoServico.CONSULTA)
            return getAutoavaliacao(agendamento);
        else
            return getExame(agendamento);
    }

    @GetMapping("/agendamento-exame")
    public ModelAndView getExame(Agendamento agendamento){
        ModelAndView mv = new ModelAndView("agendamento/lista-exames");
        
        //TEM QUE TA VENDO ISSO AQUI
        
        return mv;
    }

    @GetMapping("/agendamento-autoavaliacao")
    public ModelAndView getAutoavaliacao(Agendamento agendamento){
        ModelAndView mv = new ModelAndView("agendamento/autoavaliacao");
        
        mv.addObject("sintomas", sintomaRepository.findAll());
        mv.addObject("intensidades", Intensidade.values());
        mv.addObject("agendamento", agendamento);
        return mv;
    }

    @PostMapping("/autoavaliacao")
    public ModelAndView autoavaliacao(Agendamento agendamento, Sintoma[] sintomas){
        
        //agendamento.setEspecialidade(Autoavaliacao.getEspecialidade());
        
        return especialidade(agendamento);
    }

    @GetMapping("/agendamento-especialidade")
    public ModelAndView especialidade(Agendamento agendamento){
        ModelAndView mv = new ModelAndView("agendamento/medicos");
        
        // mv.addObject("medicos", medicoRepository.findByEspecialidadeId(agendamento.getEspecialidade().getId()));
        mv.addObject("agendamento", agendamento);
        return mv;
    }

    @GetMapping("/agendamento-medico")
    public ModelAndView agendamentoMedico(Agendamento agendamento){
        ModelAndView mv = new ModelAndView("agendamento/autoavaliacao");
        
        mv.addObject("sintomas", sintomaRepository.findAll());
        mv.addObject("agendamento", agendamento);
        return mv;
    }

    // @GetMapping("/agendamento-medicos")
    // public List<Medico> filtrarMedicos(Agendamento agendamento,
    //         @RequestParam(value = "nome", required = false) String nome,
    //         @RequestParam(value = "email", required = false) String email,
    //         @RequestParam(value = "clinica", required = false) Clinica clinica,
    //         @RequestParam(value = "crm", required = false) String crm,
    //         @RequestParam(value = "cidadeNome", required = false) String cidadeNome) {
            
    //         ModelAndView mv = new ModelAndView("agendamento/lista-medicos");
    //         var especialidade = agendamento.getEspecialidade();

    //     return medicoService.filtrarMedicos(nome, email, clinica.getId(), especialidade.getId(), crm, cidadeNome);
    // }

    // @GetMapping("/filtro-agendamento")
    // public List<Agendamento> filtrarAgendamentos(
    //         @RequestParam(value = "cidadeNome", required = false) String cidadeNome,
    //         @RequestParam(value = "clinica", required = false) Clinica clinica,
    //         @RequestParam(value = "medico", required = false) Medico medico,
    //         @RequestParam(value = "especialidade", required = false) Especialidade especialidade,
    //         @RequestParam(value = "status", required = false) Status status,
    //         @RequestParam(value = "tipoServico", required = false) TipoServico tipoServico) {

    //     return agendamentoService.filtrarAgendamentos(cidadeNome, clinica.getId(), medico.getId(), especialidade.getId(), status, tipoServico);
    // }

    // @GetMapping("/filtro-medico")
    // public List<Medico> filtrarMedicos(
    //         @RequestParam(value = "nome", required = false) String nome,
    //         @RequestParam(value = "email", required = false) String email,
    //         @RequestParam(value = "clinica", required = false) Clinica clinica,
    //         @RequestParam(value = "especialidade", required = false) Especialidade especialidade,
    //         @RequestParam(value = "crm", required = false) String crm,
    //         @RequestParam(value = "cidadeNome", required = false) String cidadeNome) {


    //     return medicoService.filtrarMedicos(nome, email, clinica.getId(), especialidade.getId(), crm, cidadeNome);
    // }

    @GetMapping
    public ModelAndView medicoHome(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv = new ModelAndView("home/index");
        // Pageable pageReq = PageRequest.of((page - 1),  2);
        // Page<Agendamento> resultPage = agendamentoRepository.findAll(pageReq);
        List<Agendamento> resultPage = agendamentoRepository.findAll();
        mv.addObject("agendamentoList", resultPage);
        return mv;
    }


    // @GetMapping("/criar")
    // public ModelAndView Criar(Agendamento agendamento){
    //     ModelAndView mv = new ModelAndView("agendamento/criar");

    //     Especialidade especialidadeSelecionada = new Especialidade("teste");

    //     List<Medico> medicos = medicoRepository.findByEspecialidadeId(especialidadeSelecionada.getId());

    //     if(medicos == null)
    //         medicos = new ArrayList<>();

    //     mv.addObject("medicos", medicos);
    //     mv.addObject("agendamento", agendamento);
    //     return mv;
    // }

    @PostMapping("/criar-agendamento")
    public ModelAndView NovoAgendamento(Agendamento agendamento){
        agendamento.setStatus(Status.ABERTO);
        agendamentoRepository.save(agendamento);
        return medicoHome(1); 
    }

    @PostMapping("/cancelar-agendamento")
    public ModelAndView CancelaAgendamento(Agendamento agendamento){
        agendamento.setStatus(Status.CANCELADO);
        agendamentoRepository.save(agendamento);
        return medicoHome(1); 
    }

    @PostMapping("/finalizar-agendamento")
    public ModelAndView FinalizarAgendamento(Agendamento agendamento){
        agendamento.setStatus(Status.FINALIZADO);
        agendamentoRepository.save(agendamento);
        return medicoHome(1); 
    }

    // @GetMapping("/export")
    // public void exportCsv(HttpServletResponse response) throws IOException {
    //     response.setContentType("text/csv");
    //     response.setHeader("Content-Disposition", "attachment; filename=relatorio.csv");
    //     this.ticketService.exportCsv(response.getWriter());
    // }
}
