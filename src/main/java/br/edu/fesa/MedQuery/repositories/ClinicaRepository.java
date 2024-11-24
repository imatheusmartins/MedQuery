package br.edu.fesa.MedQuery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Clinica;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {

    // Optional<Clinica> findByAdminId(UUID id);

    // List<Clinica> findByMatrizId(UUID matrizId);

    // @Query(value = "select * from chamado", nativeQuery = true)
    // public Page<Clinica> findAllClinicas(Pageable page);

}
