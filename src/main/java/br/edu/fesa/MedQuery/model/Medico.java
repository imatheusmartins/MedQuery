package br.edu.fesa.MedQuery.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.id.uuid.UuidGenerator;

import br.edu.fesa.MedQuery.enums.EspecialidadeEnum;
import br.edu.fesa.MedQuery.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Medico extends User{

    @Column(name = "CRM", nullable = false, unique = true, length = 20)
    private String crm;

    @ManyToOne
    @JoinColumn(name = "clinica_id_fk")
    private Clinica clinica;

    // @ManyToMany
    // @JoinTable(
    //     name = "medico_especialidade",
    //     joinColumns = @JoinColumn(name = "medico_id"),
    //     inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    // )
    private Set<EspecialidadeEnum> especialidades = new HashSet<>();

    @OneToMany(mappedBy = "medico")
    private List<Agendamento> agendamentos = new ArrayList<>();

    // public Medico(Integer id, String nome, String email, String senha, String imagem, UserRole userRole, String crm, Boolean ativo) {
    //     super(id, nome, email, senha, imagem, userRole, ativo);
    //     this.crm = crm;
    // }

    //Utilizar essa 
    public Medico(Integer id, String nome, String email, String senha, UserRole userRole, String crm, Boolean ativo) {
        super(id, nome, email, senha, userRole, ativo);
        this.crm = crm;
    }

    public Medico(String email, String senha) {
        super(email, senha); // Chama o construtor da classe User
    }

    public Medico() {
    }

    public Set<EspecialidadeEnum> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Set<EspecialidadeEnum> especialidades) {
        this.especialidades = especialidades;
    }
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }
}
