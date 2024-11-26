package br.edu.fesa.MedQuery.model;

import java.time.LocalDate;

import br.edu.fesa.MedQuery.enums.UserRole;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User { //Classe geral para usuários

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Boolean getAtivo() {
        return ativo;
    }


    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    private String nome;
    @Column(name = "EMAIL", nullable = false, unique = true, length = 20)
    private String email; 
    private String senha;
    private String imagem;
    private UserRole userRole;
    private Boolean ativo;
    private LocalDate dataAdicionado = LocalDate.now();
    private LocalDate dataApagado = LocalDate.now();

    public User(){}


    public User(Integer id, String nome, String email, String senha, String imagem, UserRole userRole, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.imagem = imagem;
        this.userRole = userRole;
        this.ativo = ativo;
    }

    public User(Integer id, String nome, String email, String senha, UserRole userRole, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.userRole = userRole;
        this.ativo = ativo;
    }

    public User(String email, String senha) {
        this.email = email;
        this.senha = senha;
        this.nome = "Nome Padrão"; // Valor padrão
        this.imagem = "default.png"; // Valor padrão
        this.userRole = UserRole.PACIENTE; // Valor padrão, supondo que você tenha um enum com 'PADRAO'
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    public UserRole getUserRole() {
        return userRole;
    }
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", imagem=" + imagem
                + ", userRole=" + userRole + "]";
    }
}
