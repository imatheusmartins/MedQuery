/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.edu.fesa.MedQuery.enums;

/**
 *
 * @author hugok
 */
public enum UserRole {
    ADMIN("admin"),
    MANAGER("manager"),
    PATIENT("patient"),
    DOCTOR("doctor");

    private String role;

    UserRole (String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
