package br.edu.fesa.MedQuery.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import br.edu.fesa.MedQuery.enums.EspecialidadeEnum;
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
    private EspecialidadeEnum especialidade;

    // @ManyToMany(mappedBy = "especialidades")
    // private Set<Medico> medicos = new HashSet<>();

    @OneToMany(mappedBy = "especialidade")
    private List<Agendamento> agendamentos = new ArrayList<>();
    

    public Especialidade(Integer id,  List<Agendamento> agendamentos) {
        this.id = id;
        //this.medicos = medicos;
        this.agendamentos = agendamentos;
    }

    public Integer getId() {
        return id;
    }

    public EspecialidadeEnum getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeEnum especialidade) {
        this.especialidade = especialidade;
    }
}
