package br.edu.fesa.MedQuery.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    @Query(value = "select * from endereco", nativeQuery = true)
    public Page<Endereco> findAllEnrecos(Pageable page);
}
