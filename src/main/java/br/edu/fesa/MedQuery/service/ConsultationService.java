package br.edu.fesa.MedQuery.service;

import java.util.Optional;
import br.edu.fesa.MedQuery.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import br.edu.fesa.MedQuery.model.SpecialtyModel;
import br.edu.fesa.MedQuery.repositories.ConsultationRepository;

@Service
public class ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;

    public List<ConsultationModel> findAll() {
        return consultationRepository.findAll();
    }   

    public Optional<ConsultationModel> findById(int id) {
        return consultationRepository.findById(id);
    }
    
    public ConsultationModel save(ConsultationModel padrao) {
        return consultationRepository.save(padrao);
    }

    public ConsultationModel update(ConsultationModel padrao) {
        return consultationRepository.save(padrao);
    }
    
    public void deleteById(int id) {
        consultationRepository.deleteById(id);
    }
}
