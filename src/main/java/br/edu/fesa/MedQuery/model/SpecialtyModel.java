package br.edu.fesa.MedQuery.model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_SPECIALTIES", schema = "MEDQUERY")
public class SpecialtyModel {
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "DESCRICAO", nullable = false, unique = true, length = 50)
    private String description;

    // @Enumerated(EnumType.STRING)
    // @Column(name = "DELETADO_DATA", nullable = true, unique = false)
    // private Date deletedDate;

    public SpecialtyModel(String description) {
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }
}
