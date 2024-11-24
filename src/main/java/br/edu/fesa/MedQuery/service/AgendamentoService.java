package br.edu.fesa.MedQuery.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.edu.fesa.MedQuery.Specification.AgendamentoSpecification;
import br.edu.fesa.MedQuery.enums.Status;
import br.edu.fesa.MedQuery.enums.TipoServico;
import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.repositories.AgendamentoRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> filtrarAgendamentos(String cidadeNome, Integer clinicaId, Integer medicoId, Integer especialidadeId, Status status, TipoServico tipoServico) {
        Specification<Agendamento> spec = Specification.where(AgendamentoSpecification.porCidade(cidadeNome))
                .and(AgendamentoSpecification.porClinica(clinicaId))
                .and(AgendamentoSpecification.porMedico(medicoId))
                .and(AgendamentoSpecification.porEspecialidade(especialidadeId))
                .and(AgendamentoSpecification.porStatus(status))
                .and(AgendamentoSpecification.porTipoServico(tipoServico));

        return agendamentoRepository.findAll(spec);
    }
}
