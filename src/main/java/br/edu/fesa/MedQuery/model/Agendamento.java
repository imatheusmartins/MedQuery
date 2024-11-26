package br.edu.fesa.MedQuery.model;

import java.time.LocalDate;

import br.edu.fesa.MedQuery.enums.Status;
import br.edu.fesa.MedQuery.enums.TipoServico;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Agendamento  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataCriacao = LocalDate.now();
    private LocalDate dataAgendada;
    private String laudo;
    private TipoServico tipoServico;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "clinica_id_fk")
    private Clinica clinica;

    @ManyToOne
    @JoinColumn(name = "paciente_id_fk")
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id_fk")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "especialidade_id_fk")
    private Especialidade especialidade;

    // public Agendamento(Integer id, LocalDate dataCriacao, LocalDate dataAgendada, String laudo, TipoServico tipoServico,
    //         Status status, Clinica clinica, Paciente paciente, Medico medico, Especialidade especialidade) {
    //     this.id = id;
    //     this.dataCriacao = dataCriacao;
    //     this.dataAgendada = dataAgendada;
    //     this.laudo = laudo;
    //     this.tipoServico = tipoServico;
    //     this.status = status;
    //     this.clinica = clinica;
    //     this.paciente = paciente;
    //     this.medico = medico;
    //     this.especialidade = especialidade;
    // }

    public Agendamento(TipoServico tipoServico){
        this.tipoServico = tipoServico;
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDataAgendada(LocalDate dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public LocalDate getDataAgendada() {
        return dataAgendada;
    }

    public String getLaudo() {
        return laudo;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public Clinica getCliente() {
        return clinica;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
