package br.edu.fesa.MedQuery.repositories;

import br.edu.fesa.MedQuery.model.*;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityRepository extends JpaRepository<UnityModel, Integer> {
    Optional<UnityModel> findByNome(String nome);
}
