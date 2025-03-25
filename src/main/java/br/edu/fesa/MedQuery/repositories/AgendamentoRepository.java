package br.edu.fesa.MedQuery.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.enums.Status;
import br.edu.fesa.MedQuery.enums.TipoServico;
import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Medico;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>, JpaSpecificationExecutor<Agendamento> {

    @Query(value = "select * from agendamento", nativeQuery = true)
    public Page<Agendamento> findAllAgendamentos(Pageable page);

    List<Agendamento> findByStatusOrderByDataCriacaoAsc(Status status);

    List<Agendamento> findByStatusOrderByDataAgendadaAsc(Status status);

    long countByStatus(Status status);

    @Query("SELECT a FROM Agendamento a JOIN FETCH a.clinica JOIN FETCH a.medico")
    List<Agendamento> findAllWithClinicaAndMedico();

    // List<Agendamento> findByStatusAndPessoa_Id(Status status, Integer pessoaId);

    // Filtrar agendamentos por cidade
    // @Query("SELECT a FROM Agendamento a WHERE a.clinica.endereco.cidade.nome = :cidadeNome")
    // List<Agendamento> findByCidade(String cidadeNome);

    // Filtrar agendamentos por clínica
    // @Query("SELECT a FROM Agendamento a WHERE a.clinica.id = :clinicaId")
    // List<Agendamento> findByClinica(Integer clinicaId);

    // // Filtrar agendamentos por médico
    // @Query("SELECT a FROM Agendamento a WHERE a.medico.id = :medicoId")
    // List<Agendamento> findByMedico(Integer medicoId);

    // // Filtrar agendamentos por especialidade
    // @Query("SELECT a FROM Agendamento a WHERE a.especialidade.id = :especialidadeId")
    // List<Agendamento> findByEspecialidade(Integer especialidadeId);
    
    // // Filtrar agendamentos por status
    // List<Agendamento> findByStatus(Status status);
    
    // // Filtrar agendamentos por tipo de serviço
    // List<Agendamento> findByTipoServico(TipoServico tipoServico);
    
    // Filtrar agendamentos por cidade e data
    // @Query("SELECT a FROM Agendamento a WHERE a.clinica.endereco.cidade.nome = :cidadeNome AND a.dataAgendada = :dataAgendada")
    // List<Agendamento> findByCidadeAndData(String cidadeNome, LocalDate dataAgendada);

    // Filtrar agendamentos a partir de uma data (inclusive)
    // List<Agendamento> findByDataAgendadaAfterOrDataAgendadaEquals(LocalDate dataInicial);

    // // Filtrar agendamentos antes de uma data (inclusive)
    // List<Agendamento> findByDataAgendadaBeforeOrDataAgendadaEquals(LocalDate dataFinal);

    // // Filtrar agendamentos entre duas datas (inclusive)
    // List<Agendamento> findByDataAgendadaBetween(LocalDate dataInicial, LocalDate dataFinal);
}
