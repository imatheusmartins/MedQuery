package br.edu.fesa.MedQuery.service;

import br.edu.fesa.MedQuery.repositories.*;
import br.edu.fesa.MedQuery.model.*;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    public List<SpecialtyModel> findAll() {
        return specialtyRepository.findAll();
    }

    public Optional<SpecialtyModel> findById(int id) {
        return specialtyRepository.findById(id);
    }

    public Optional<SpecialtyModel> findByNome(String description) {
        return specialtyRepository.findByNome(description);
    }
    
    public SpecialtyModel save(SpecialtyModel padrao) {
        return specialtyRepository.save(padrao);
    }

    public SpecialtyModel update(SpecialtyModel padrao) {
        return specialtyRepository.save(padrao);
    }
    
    public void deleteById(int id) {
        specialtyRepository.deleteById(id);
    }
}
