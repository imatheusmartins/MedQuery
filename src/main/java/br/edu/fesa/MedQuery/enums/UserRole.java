package br.edu.fesa.MedQuery.enums;

public enum UserRole {
    ADMIN("admin"),
    ADMINISTRADOR("administrador"),
    PACIENTE("paciente"),
    MEDICO("medico");

    private String role;

    UserRole (String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
