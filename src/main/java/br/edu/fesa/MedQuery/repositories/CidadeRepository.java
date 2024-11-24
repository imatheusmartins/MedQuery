package br.edu.fesa.MedQuery.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query(value = "select * from cidade", nativeQuery = true)
    public Page<Cidade> findAllCidades(Pageable page);
}
