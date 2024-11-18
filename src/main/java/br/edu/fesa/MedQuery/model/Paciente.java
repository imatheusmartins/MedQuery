package br.edu.fesa.MedQuery.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.fesa.MedQuery.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Paciente extends User{

    public Paciente(Integer id, String nome, String email, String senha, String imagem, UserRole perfil) {
        super(id, nome, email, senha, imagem, perfil);
    }

    public Paciente() {
    }

    @OneToMany(mappedBy = "paciente")
    private List<Agendamento> agendamentos = new ArrayList<>();

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setChamados(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
