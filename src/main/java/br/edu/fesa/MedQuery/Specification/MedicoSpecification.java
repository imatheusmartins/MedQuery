package br.edu.fesa.MedQuery.Specification;

import org.springframework.data.jpa.domain.Specification;

import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.model.Especialidade;
import br.edu.fesa.MedQuery.model.Medico;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class MedicoSpecification {

    public static Specification<Medico> porNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null || nome.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Medico> porEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.isEmpty()) {
                return null;
            }
            return criteriaBuilder.equal(criteriaBuilder.lower(root.get("email")), email.toLowerCase());
        };
    }

    public static Specification<Medico> porClinica(Integer clinicaId) {
        return (root, query, criteriaBuilder) -> {
            if (clinicaId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("clinica").get("id"), clinicaId);
        };
    }

    public static Specification<Medico> porEspecialidade(Integer especialidadeId) {
        return (root, query, criteriaBuilder) -> {
            if (especialidadeId == null) {
                return null;
            }
            Join<Medico, Especialidade> especialidades = root.join("especialidades", JoinType.LEFT);
            return criteriaBuilder.equal(especialidades.get("id"), especialidadeId);
        };
    }

    public static Specification<Medico> porCrm(String crm) {
        return (root, query, criteriaBuilder) -> {
            if (crm == null || crm.isEmpty()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("crm"), crm);
        };
    }
}
