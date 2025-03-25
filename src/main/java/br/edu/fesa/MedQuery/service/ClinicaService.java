package br.edu.fesa.MedQuery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.fesa.MedQuery.model.Clinica;
import br.edu.fesa.MedQuery.repositories.ClinicaRepository;

@Service
public class ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    public List<Clinica> findAll() {
        return clinicaRepository.findAll(); 
    }
}