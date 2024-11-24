package br.edu.fesa.MedQuery.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Clinica;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {

    Optional<Clinica> findByAdminId(Integer id);

    // List<Clinica> findByMatrizId(Integer matrizId);

    @Query(value = "select * from clinica", nativeQuery = true)
    public Page<Clinica> findAllClinicas(Pageable page);

}
