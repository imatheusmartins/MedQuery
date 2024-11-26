package br.edu.fesa.MedQuery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.edu.fesa.MedQuery.Specification.MedicoSpecification;
import br.edu.fesa.MedQuery.model.Medico;
import br.edu.fesa.MedQuery.repositories.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    // public List<Medico> filtrarMedicos(String nome, String email, Integer clinicaId, Integer especialidadeId, String crm, String cidadeNome) {
    //     Specification<Medico> spec = Specification.where(MedicoSpecification.porNome(nome))
    //             .and(MedicoSpecification.porEmail(email))
    //             .and(MedicoSpecification.porClinica(clinicaId))
    //             .and(MedicoSpecification.porEspecialidade(especialidadeId))
    //             .and(MedicoSpecification.porCrm(crm))
    //             .and(MedicoSpecification.porCidade(cidadeNome));

    //     return medicoRepository.findAll(spec);
    // }
}
