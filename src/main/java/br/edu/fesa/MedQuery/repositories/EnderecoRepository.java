package br.edu.fesa.MedQuery.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

    Optional<Endereco> findById(UUID id);
}
