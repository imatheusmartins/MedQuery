package br.edu.fesa.MedQuery.Specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.edu.fesa.MedQuery.enums.Status;
import br.edu.fesa.MedQuery.enums.TipoServico;
import br.edu.fesa.MedQuery.model.Agendamento;

public class AgendamentoSpecification {

    

    public static Specification<Agendamento> porClinica(Integer clinicaId) {
        return (root, query, builder) -> clinicaId == null ? null :
                builder.equal(root.get("clinica").get("id"), clinicaId);
    }

    public static Specification<Agendamento> porMedico(Integer medicoId) {
        return (root, query, builder) -> medicoId == null ? null :
                builder.equal(root.get("medico").get("id"), medicoId);
    }

    public static Specification<Agendamento> porEspecialidade(Integer especialidadeId) {
        return (root, query, builder) -> especialidadeId == null ? null :
                builder.equal(root.get("especialidade").get("id"), especialidadeId);
    }

    public static Specification<Agendamento> porStatus(Status status) {
        return (root, query, builder) -> status == null ? null :
                builder.equal(root.get("status"), status);
    }

    public static Specification<Agendamento> porTipoServico(TipoServico tipoServico) {
        return (root, query, builder) -> tipoServico == null ? null :
                builder.equal(root.get("tipoServico"), tipoServico);
    }
}
