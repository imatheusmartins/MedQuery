package br.edu.fesa.MedQuery.repositories;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByEmail(String email);

    @Query(value = "select * from paciente", nativeQuery = true)
    public Page<Paciente> findAllPacientes(Pageable page);
    
}