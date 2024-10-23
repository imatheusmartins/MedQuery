/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.MedQuery.model;

import br.edu.fesa.MedQuery.enums.UserRole;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "DESCRICAO", nullable = false, unique = true, length = 50)
    private String description;
    @Column(name = "ENDEREÇO", nullable = false, unique = false)
    private String address;
    @Enumerated(EnumType.STRING)
    private int userManagerId;

    public UnityModel(String description, String address, int userManagerId) {
        this.description = description;
        this.address = address;
        this.userManagerId = userManagerId;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAddress() {
        return this.address;
    }

    public int getUserManagerId() {
        return this.userManagerId;
    }
}
