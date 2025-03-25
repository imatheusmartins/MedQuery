package br.edu.fesa.MedQuery.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Gestor;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Integer> {

    Optional<Gestor> findByEmail(String email);

    @Query(value = "select * from gestor", nativeQuery = true)
    public Page<Gestor> findAllGestores(Pageable page);
}
