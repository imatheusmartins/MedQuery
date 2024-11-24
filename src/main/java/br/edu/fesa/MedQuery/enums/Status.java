package br.edu.fesa.MedQuery.enums;

public enum Status {

    ABERTO("Aberto"),
    CANCELADO("Cancelado"),
    FINALIZADO("Finalizado");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
