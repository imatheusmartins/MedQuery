package br.edu.fesa.MedQuery.repositories;

import br.edu.fesa.MedQuery.model.*;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<SpecialtyModel, Integer> {
    SpecialtyModel findById(int id);
    SpecialtyModel findByDescription(String description);
}
