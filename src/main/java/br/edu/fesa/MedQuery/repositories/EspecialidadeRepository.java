package br.edu.fesa.MedQuery.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.model.Especialidade;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer>{

    Optional<Especialidade> findById(Integer id);

    @Query(value = "select * from especialidade", nativeQuery = true)
    public Page<Especialidade> findAllEspecialidades(Pageable page);
}
