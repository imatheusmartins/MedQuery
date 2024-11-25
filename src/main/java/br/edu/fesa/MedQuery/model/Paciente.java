package br.edu.fesa.MedQuery.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.edu.fesa.MedQuery.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Paciente extends User{

    @OneToMany(mappedBy = "paciente")
    private List<Agendamento> agendamentos = new ArrayList<>();

    public Paciente(Integer id, String nome, String email, String senha, String imagem, UserRole userRole) {
        super(id, nome, email, senha, imagem, userRole);
    }

    public Paciente(String email, String senha) {
        super(email, senha); // Chama o construtor da classe User
    }

    public Paciente() {
    }
}
