package br.edu.fesa.MedQuery.service;

import br.edu.fesa.MedQuery.repositories.*;
import br.edu.fesa.MedQuery.model.*;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnityService {
    
    @Autowired
    private UnityRepository unityRepository;

    public List<UnityModel> findAll() {
        return unityRepository.findAll();
    }

    public Optional<UnityModel> findById(int id) {
        return unityRepository.findById(id);
    }

    public Optional<UnityModel> findByNome(String nome) {
        return unityRepository.findByNome(nome);
    }
    
    public UnityModel save(UnityModel padrao) {
        return unityRepository.save(padrao);
    }

    public UnityModel update(UnityModel padrao) {
        return unityRepository.save(padrao);
    }
    
    public void deleteById(int id) {
        unityRepository.deleteById(id);
    }
}
