package br.edu.fesa.MedQuery.enums;

public enum UserRole {
    ADMIN("admin"),
    GESTOR("gestor"),
    PACIENTE("paciente"),
    MEDICO("medico");

    private String userRole;

    private UserRole(String userRole){
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}