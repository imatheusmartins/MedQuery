package br.edu.fesa.MedQuery.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.fesa.MedQuery.model.Especialidade;
import br.edu.fesa.MedQuery.enums.EspecialidadeEnum;
import br.edu.fesa.MedQuery.enums.Sintoma;
import br.edu.fesa.MedQuery.enums.Status;
import br.edu.fesa.MedQuery.enums.TipoServico;
import br.edu.fesa.MedQuery.enums.UserRole;
import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.model.Medico;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;
import br.edu.fesa.MedQuery.repositories.ClinicaRepository;
import br.edu.fesa.MedQuery.repositories.MedicoRepository;
import br.edu.fesa.MedQuery.repositories.SintomaRepository;
import br.edu.fesa.MedQuery.service.AgendamentoService;
import br.edu.fesa.MedQuery.service.MedicoService;
import br.edu.fesa.MedQuery.util.Autoavaliacao;
import br.edu.fesa.MedQuery.util.PasswordUtil;

@Controller
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    Agendamento agendamentoConst;

    // @Autowired
    // private final MedicoRepository medicoRepository;
    
    // private final SintomaRepository sintomaRepository;

    // private final AgendamentoService agendamentoService;
    // private final MedicoService medicoService;

    // public AgendamentoController(MedicoRepository medicoRepository, AgendamentoRepository agendamentoRepository, AgendamentoService agendamentoService, 
    // MedicoService medicoService, SintomaRepository sintomaRepository) {
    //     this.medicoRepository = medicoRepository;
    //     this.agendamentoRepository = agendamentoRepository;
    //     this.agendamentoService = agendamentoService;
    //     this.medicoService = medicoService;
    //     // this.sintomaRepository = sintomaRepository;
    // }

    @GetMapping("/autoavaliacao")
    public ModelAndView autoavaliacao(Agendamento agendamento){
        ModelAndView mv =  new ModelAndView("agendamento/autoavaliacao");

        mv.addObject("sintomasEnum", Sintoma.values());
        mv.addObject("agendamento", agendamento);

        return mv;
    }

    @PostMapping("/lista-clinicas")
    public ModelAndView getListClinicas(@ModelAttribute Agendamento agendamento){
        ModelAndView mv =  new ModelAndView("agendamento/lista-clinicas");

        mv.addObject("agendamento", agendamento);

        agendamentoConst = agendamento;

        mv.addObject("clinicas", clinicaRepository.findAll());
        mv.addObject("medicosList", medicoRepository.findAll());
        return mv;
    }

    @GetMapping("/lista-medico")
    public ModelAndView getListMedico(@RequestParam String clinicaId){
        ModelAndView mv =  new ModelAndView("agendamento/lista-medicos");

        Integer clinicaIdInt = Integer.parseInt(clinicaId);

        Clinica clinica = clinicaRepository.findById(clinicaIdInt)
                                            .orElseThrow(() -> new RuntimeException("Clínica não encontrada"));

        agendamentoConst.setClinica(clinica);
        //Fazer atribuição de clinica no agendamento

        mv.addObject("medicos", medicoRepository.findAll());

        return mv;
    }

    @GetMapping("/agendamento")
    public ModelAndView getAgendamento(@RequestParam String medicoId){
        ModelAndView mv =  new ModelAndView("agendamento/agendamento");

        Integer medicoIdInt = Integer.parseInt(medicoId);

        Medico medico = medicoRepository.findById(medicoIdInt)
                                            .orElseThrow(() -> new RuntimeException("Médico não encontrada"));

        agendamentoConst.setMedico(medico);

        Agendamento agendamento = agendamentoConst;


        //Fazer atribuição de clinica no agendamento
        mv.addObject("agendamento", agendamento);

        return mv;
    }

    @PostMapping("/salvar")
    public String postSalvarAgendamento(@ModelAttribute Agendamento agendamento){
        ModelAndView mv =  new ModelAndView("agendamento/agendamento");

        agendamentoConst.setData(agendamento.getData());
        agendamentoConst.setHora(agendamento.getHora());

        try {
            agendamentoRepository.save(agendamentoConst);
            System.out.println("Salvo com sucesso: " + agendamentoConst.getId());
            return "redirect:/home";
        } catch (Exception e) {
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar " + e.getMessage());
            return "redirect:/home";
        }
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

    @GetMapping("/agendamento-exame")
    public ModelAndView getExame(Agendamento agendamento){
        ModelAndView mv = new ModelAndView("agendamento/lista-exames");
        
        //TEM QUE TA VENDO ISSO AQUI
        
        return mv;
    }


    @PostMapping("/autoavaliacao")
    public ModelAndView autoavaliacao(Agendamento agendamento, Sintoma[] sintomas){
        
        //agendamento.setEspecialidade(Autoavaliacao.getEspecialidade());
        
        return getMedicos(agendamento);
    }

    @GetMapping("/agendamento-medicos")
    public ModelAndView getMedicos(Agendamento agendamento){
        ModelAndView mv = new ModelAndView("agendamento/medicos");
        
        // mv.addObject("medicos", medicoRepository.findByEspecialidadeId(agendamento.getEspecialidade().getId()));
        mv.addObject("agendamento", agendamento);
        return mv;
    }

    @GetMapping("/agendamento-medico")
    public ModelAndView agendamentoMedico(Agendamento agendamento){
        ModelAndView mv = new ModelAndView("agendamento/medico");
        
        mv.addObject("agendamento", agendamento);
        return mv;
    }

    // @GetMapping("/agendamento-horario")
    // public String horario(@ModelAttribute Agendamento agendamento, RedirectAttributes redirectAttributes, Model model) {
    //     LocalDateTime agora = LocalDateTime.now();
    //     LocalDateTime dataAgendada = agendamento.getDataAgendada();

    //     // Verifica se a data agendada é a partir do dia seguinte e no máximo 30 dias após o dia atual
    //     if (dataAgendada == null || dataAgendada.toLocalDate().isBefore(agora.toLocalDate().plusDays(1)) || 
    //         dataAgendada.toLocalDate().isAfter(agora.toLocalDate().plusDays(30))) {
    //         model.addAttribute("error", "A data deve ser a partir de amanhã e no máximo 30 dias após hoje.");
    //         return "agendamento/medico";
    //     }

    // // Verifica se o horário está entre 07:00 e 17:30
    // LocalTime horarioAgendado = dataAgendada.toLocalTime();
    // LocalTime inicioHorario = LocalTime.of(7, 0);
    // LocalTime fimHorario = LocalTime.of(17, 30);
    // if (horarioAgendado.isBefore(inicioHorario) || horarioAgendado.isAfter(fimHorario)) {
    //     model.addAttribute("error", "O horário deve ser entre 07:00 e 17:30.");
    //     return "agendamento/medico";
    // }

    // // Verifica se o médico possui algum agendamento com menos de 15 minutos de diferença
    // List<Agendamento> agendamentosMedico = medicoService.buscarAgendamentosPorMedicoEData(agendamento.getMedico(), dataAgendada);
    // for (Agendamento ag : agendamentosMedico) {
    //     LocalDateTime horarioExistente = ag.getDataAgendada();
    //     if (Math.abs(Duration.between(horarioExistente, dataAgendada).toMinutes()) < 15) {
    //         model.addAttribute("error", "O médico já possui um agendamento próximo ao horário desejado.");
    //         return "agendamento/medico";
    //     }
    // }

    // // Se todas as verificações passarem, salve o agendamento e redirecione
    //     agendamentoRepository.save(agendamento);
    //     redirectAttributes.addFlashAttribute("successMessage", "Agendamento realizado com sucesso!");
    //     return "redirect:/paciente/lista-agendamentos";
    // }

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

    // @GetMapping
    // public ModelAndView medicoHome(@RequestParam(defaultValue = "1") int page){
    //     ModelAndView mv = new ModelAndView("home/index");
    //     // Pageable pageReq = PageRequest.of((page - 1),  2);
    //     // Page<Agendamento> resultPage = agendamentoRepository.findAll(pageReq);
    //     List<Agendamento> resultPage = agendamentoRepository.findAll();
    //     mv.addObject("agendamentoList", resultPage);
    //     return mv;
    // }


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

    // @PostMapping("/criar-agendamento")
    // public ModelAndView NovoAgendamento(Agendamento agendamento){
    //     agendamento.setStatus(Status.ABERTO);
    //     agendamentoRepository.save(agendamento);
    //     return medicoHome(1); 
    // }

    // @PostMapping("/cancelar-agendamento")
    // public ModelAndView CancelaAgendamento(Agendamento agendamento){
    //     agendamento.setStatus(Status.CANCELADO);
    //     agendamentoRepository.save(agendamento);
    //     return medicoHome(1); 
    // }

    // @PostMapping("/finalizar-agendamento")
    // public ModelAndView FinalizarAgendamento(Agendamento agendamento){
    //     agendamento.setStatus(Status.FINALIZADO);
    //     agendamentoRepository.save(agendamento);
    //     return medicoHome(1); 
    // }

    // @GetMapping("/export")
    // public void exportCsv(HttpServletResponse response) throws IOException {
    //     response.setContentType("text/csv");
    //     response.setHeader("Content-Disposition", "attachment; filename=relatorio.csv");
    //     this.ticketService.exportCsv(response.getWriter());
    // }
}
