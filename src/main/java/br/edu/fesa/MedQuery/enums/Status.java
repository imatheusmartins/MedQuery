package br.edu.fesa.MedQuery.enums;

public enum Status {

    ABERTO("Aberto"),
    CANCELADO("Cancelado"),
    FECHADO("Fechado");

    private String status;

    private Status(String status) {
        this.status = status;
    }

    public String getChamadoTicket() {
        return status;
    }

    public void setChamadoTicket(String status) {
        this.status = status;
    }
}
