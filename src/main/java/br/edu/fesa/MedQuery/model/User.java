package br.edu.fesa.MedQuery.model;

import br.edu.fesa.MedQuery.enums.UserRole;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User { //Classe geral para usuários

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email; 
    private String senha;
    private String imagem;
    private UserRole perfil;

    public User(){}


    public User(Integer id, String nome, String email, String senha, String imagem, UserRole perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.imagem = imagem;
        this.perfil = perfil;
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
    public UserRole getPerfil() {
        return perfil;
    }
    public void setPerfil(UserRole perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", imagem=" + imagem
                + ", perfil=" + perfil + "]";
    }
}
