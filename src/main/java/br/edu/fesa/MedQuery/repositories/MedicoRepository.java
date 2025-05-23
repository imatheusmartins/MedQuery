package br.edu.fesa.MedQuery.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Agendamento;
import br.edu.fesa.MedQuery.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer>, JpaSpecificationExecutor<Medico> {
    
    Optional<Medico> findByEmail(String email);

    // @Query(value = "select m from Medico m join m.especialidades e where e.id = :especialidadeId")
    // List<Medico> findByEspecialidadeId(@Param("especialidadeId") Integer especialidadeId);

    @Query(value = "select *  from medico", nativeQuery = true)
    public Page<Medico> findAllMedicos(Pageable page);

    @Query("SELECT a FROM Agendamento a WHERE a.medico.id = :medicoId AND DATE(a.dataAgendada) = :data")
    List<Agendamento> buscarAgendamentosPorMedicoEData(@Param("medicoId") Integer medicoId, @Param("data") LocalDate data);

}
