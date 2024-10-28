package br.edu.fesa.MedQuery.repositories;

import br.edu.fesa.MedQuery.model.*;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConsultationRepository extends JpaRepository<ConsultationModel, Integer> {

    ConsultationModel findById(int id);
    List<ConsultationModel> findByPatientId(int patientId);
    
    List<ConsultationModel> findByDoctorId(int doctorId);
    
    List<ConsultationModel> findByUnityId(int unityId);
}
