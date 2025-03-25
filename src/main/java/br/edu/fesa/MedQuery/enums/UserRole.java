package br.edu.fesa.MedQuery.enums;

public enum UserRole {
    ADMIN("Admin"),
    GESTOR("Gestor"),
    PACIENTE("Paciente"),
    MEDICO("Médico");

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