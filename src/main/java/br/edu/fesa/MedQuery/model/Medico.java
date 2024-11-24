package br.edu.fesa.MedQuery.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.id.uuid.UuidGenerator;

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

    @OneToMany
    @JoinColumn(name = "clinica_id_fk")
    private Clinica clinica;

    @ManyToMany
    @JoinTable(
        name = "medico_especialidade",
        joinColumns = @JoinColumn(name = "medico_id"),
        inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private Set<Especialidade> courses = new HashSet<>();

    public Medico(UUID id, String nome, String email, String senha, String imagem, UserRole perfil, String crm) {
        super(id, nome, email, senha, imagem, perfil);
        this.crm = crm;
    }

    public Medico(String email, String senha) {
        super(email, senha); // Chama o construtor da classe User
    }

    public Medico() {
    }
}
