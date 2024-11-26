package br.edu.fesa.MedQuery.enums;

public enum Cidade {
    SAO_PAULO("São Paulo"),
    GUARULHOS("Guarulhos"),
    CAMPINAS("Campinas"),
    SAO_BERNARDO_DO_CAMPO("São Bernardo do Campo"),
    SANTO_ANDRE("Santo André"),
    OSASCO("Osasco"),
    SOROCABA("Sorocaba"),
    MAUA("Mauá"),
    SAO_JOSE_DOS_CAMPOS("São José dos Campos"),
    RIBEIRAO_PRETO("Ribeirão Preto");

    private String cidade;

    private Cidade(String cidade){
        this.cidade = cidade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}