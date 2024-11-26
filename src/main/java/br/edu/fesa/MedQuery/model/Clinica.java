package br.edu.fesa.MedQuery.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String imagem;

    @OneToOne
    @JoinColumn(name = "endereco_id_fk")
    private Endereco endereco;

    // @ManyToOne
    // @JoinColumn(name = "matriz_id")
    // private Clinica matriz;

    // @OneToMany(mappedBy = "matriz")
    // private Set<Clinica> filiais = new HashSet<>();

    // public Clinica(Integer id, String nome, String imagem, Endereco endereco, Gestor gestor, List<Medico> medicos,
    //         List<Agendamento> agendamentos) {
    //     this.id = id;
    //     this.nome = nome;
    //     this.imagem = imagem;
    //     this.endereco = endereco;
    //     this.gestor = gestor;
    //     this.medicos = medicos;
    //     this.agendamentos = agendamentos;
    // }

    @OneToOne(mappedBy = "clinica")
    private Gestor gestor;

    @OneToMany(mappedBy = "clinica")
    private List<Medico> medicos = new ArrayList<>();

    @OneToMany(mappedBy = "clinica")
    private List<Agendamento> agendamentos = new ArrayList<>(); 

    public Clinica (){};

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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    // public Clinica getMatriz() {
    //     return matriz;
    // }

    // public void setMatriz(Clinica matriz) {
    //     this.matriz = matriz;
    // }

    // public Gestor getGestor() {
    //     return gestor;
    // }

    // public void setGestor(Gestor gestor) {
    //     this.gestor = gestor;
    // }

    
}
