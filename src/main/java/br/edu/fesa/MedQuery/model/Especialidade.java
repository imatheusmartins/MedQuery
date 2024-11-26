package br.edu.fesa.MedQuery.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Especialidade {

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;

    @ManyToMany(mappedBy = "especialidades")
    private Set<Medico> medicos = new HashSet<>();

    @OneToMany(mappedBy = "especialidade")
    private List<Agendamento> agendamentos = new ArrayList<>();
    
    public Especialidade(String nome) {
        this.nome = nome;
    }

    public Especialidade(Integer id, String nome, Set<Medico> medicos, List<Agendamento> agendamentos) {
        this.id = id;
        this.nome = nome;
        this.medicos = medicos;
        this.agendamentos = agendamentos;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
