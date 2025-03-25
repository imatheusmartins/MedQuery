package br.edu.fesa.MedQuery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.edu.fesa.MedQuery.model.Sintoma;

@Repository
public interface SintomaRepository extends JpaRepository<Sintoma, Integer>, JpaSpecificationExecutor<Sintoma> {

}
