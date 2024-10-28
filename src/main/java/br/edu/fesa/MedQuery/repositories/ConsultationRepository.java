package br.edu.fesa.MedQuery.repositories;

import br.edu.fesa.MedQuery.model.*;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConsultationRepository extends JpaRepository<ConsultationModel, Integer> {

    Optional<List<ConsultationModel>> findByPatientId(int patientId);
    
    Optional<List<ConsultationModel>> findByDoctorId(int doctorId);
    
    Optional<List<ConsultationModel>> findByUnityId(int unityId);
}
