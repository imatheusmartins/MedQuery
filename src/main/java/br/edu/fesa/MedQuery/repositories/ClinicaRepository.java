package br.edu.fesa.MedQuery.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Clinica;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {

    List<Clinica> findByNome(String nome);

    // Buscar clínicas que possuem um determinado gestor
    @Query("SELECT c FROM Clinica c WHERE c.gestor.id = :gestorId")
    List<Clinica> findByGestorId(Integer gestorId);


    // Encontrar clínicas com um número específico de médicos (pelo menos x médicos)
    @Query("SELECT c FROM Clinica c WHERE SIZE(c.medicos) >= :quantidadeMedicos")
    List<Clinica> findClinicasComMaisMedicos(Integer quantidadeMedicos);

    // Buscar clínicas que possuem agendamentos
    @Query("SELECT c FROM Clinica c WHERE c.agendamentos IS NOT EMPTY")
    List<Clinica> findClinicasComAgendamentos();

    // @Query("SELECT DISTINCT c FROM Clinica c JOIN c.medicos m JOIN m.especialidades e WHERE e.nome = :especialidade")
    // List<Clinica> findClinicasByMedicoEspecialidade(@Param("especialidade") String especialidade);

}
