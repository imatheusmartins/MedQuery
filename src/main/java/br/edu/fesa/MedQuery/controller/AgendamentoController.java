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
import br.edu.fesa.MedQuery.enums.Status;
import br.edu.fesa.MedQuery.enums.TipoServico;
import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.model.Medico;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.MedicoRepository;
import br.edu.fesa.MedQuery.service.AgendamentoService;
import br.edu.fesa.MedQuery.service.MedicoService;

@Controller
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final MedicoRepository medicoRepository;
    private final AgendamentoRepository agendamentoRepository;

    private final AgendamentoService agendamentoService;
    private MedicoService medicoService;

    public AgendamentoController(MedicoRepository medicoRepository, AgendamentoRepository agendamentoRepository, AgendamentoService agendamentoService, MedicoService medicoService) {
        this.medicoRepository = medicoRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoService = agendamentoService;
        this.medicoService = medicoService;
    }

    @GetMapping("/filtro-agendamento")
    public List<Agendamento> filtrarAgendamentos(
            @RequestParam(value = "cidadeNome", required = false) String cidadeNome,
            @RequestParam(value = "clinica", required = false) Clinica clinica,
            @RequestParam(value = "meeico", required = false) Medico medico,
            @RequestParam(value = "especialidade", required = false) Especialidade especialidade,
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "tipoServico", required = false) TipoServico tipoServico) {

        return agendamentoService.filtrarAgendamentos(cidadeNome, clinica.getId(), medico.getId(), especialidade.getId(), status, tipoServico);
    }

    @GetMapping("/filtro-medico")
    public List<Medico> filtrarMedicos(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "clinica", required = false) Clinica clinica,
            @RequestParam(value = "especialidade", required = false) Especialidade especialidade,
            @RequestParam(value = "crm", required = false) String crm,
            @RequestParam(value = "cidadeNome", required = false) String cidadeNome) {

        return medicoService.filtrarMedicos(nome, email, clinica.getId(), especialidade.getId(), crm, cidadeNome);
    }

    @GetMapping
    public ModelAndView chamadoHome(@RequestParam(defaultValue = "1") int page){
        ModelAndView mv = new ModelAndView("home/index");
        // Pageable pageReq = PageRequest.of((page - 1),  2);
        // Page<Agendamento> resultPage = agendamentoRepository.findAll(pageReq);
        List<Agendamento> resultPage = agendamentoRepository.findAll();
        mv.addObject("agendamentoList", resultPage);
        return mv;
    }


    @GetMapping("/criar")
    public ModelAndView Criar(Agendamento agendamento){
        ModelAndView mv = new ModelAndView("agendamento/criar");

        Especialidade especialidadeSelecionada = new Especialidade("teste");

        List<Medico> medicos = medicoRepository.findByEspecialidadeId(especialidadeSelecionada.getId());

        if(medicos == null)
            medicos = new ArrayList<>();

        mv.addObject("medicos", medicos);
        mv.addObject("agendamento", agendamento);
        return mv;
    }

    @PostMapping("/criar-agendamento")
    public ModelAndView NovoAgendamento(Agendamento agendamento){
        agendamento.setStatus(Status.ABERTO);
        agendamentoRepository.save(agendamento);
        return chamadoHome(1); 
    }

    @PostMapping("/cancelar-agendamento")
    public ModelAndView CancelaAgendamento(Agendamento agendamento){
        agendamento.setStatus(Status.CANCELADO);
        agendamentoRepository.save(agendamento);
        return chamadoHome(1); 
    }

    @PostMapping("/finalizar-agendamento")
    public ModelAndView FinalizarAgendamento(Agendamento agendamento){
        agendamento.setStatus(Status.FINALIZADO);
        agendamentoRepository.save(agendamento);
        return chamadoHome(1); 
    }


    // @GetMapping("/export")
    // public void exportCsv(HttpServletResponse response) throws IOException {
    //     response.setContentType("text/csv");
    //     response.setHeader("Content-Disposition", "attachment; filename=relatorio.csv");
    //     this.ticketService.exportCsv(response.getWriter());
    // }
}
