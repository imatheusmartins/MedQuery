/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.MedQuery.model;
import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author 081220009
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_UNITIES", schema = "MEDQUERY")
public class UnityModel {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NOME", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "ENDEREÇO", nullable = false, unique = false)
    private String address;

    @Column(name = "GESTOR", nullable = true, unique = false)
    private int userManagerId;

    // @Enumerated(EnumType.STRING)
    // @Column(name = "DELETADO_DATA", nullable = true, unique = false)
    // private Date deletedDate;

    public UnityModel(String name, String address, int userManagerId) {
        this.name = name;
        this.address = address;
        this.userManagerId = userManagerId;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public int getUserManagerId() {
        return this.userManagerId;
    }
}
