package br.edu.fesa.MedQuery.model;

import java.util.UUID;

import br.edu.fesa.MedQuery.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Gestor extends User {

    @OneToOne
    @JoinColumn(name = "clinica_id_fk")
    private Clinica clinica;

    public Gestor(UUID id, String nome, String email, String senha, String imagem, UserRole perfil) {
        super(id, nome, email, senha, imagem, perfil);
    }

    public Gestor(String email, String senha) {
        super(email, senha); // Chama o construtor da classe User
    }

    public Gestor() {
    }

}
