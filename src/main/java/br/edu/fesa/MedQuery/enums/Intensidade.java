package br.edu.fesa.MedQuery.enums;

public enum Intensidade {
    LEVE("Leve"),
    MODERADO("Moderado"),
    INTENSO("Intenso"),
    SEVERO("Severo");


    private String intensidade;

    private Intensidade(String intensidade) {
        this.intensidade = intensidade;
    }

    public String getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(String intensidade) {
        this.intensidade = intensidade;
    }
}
