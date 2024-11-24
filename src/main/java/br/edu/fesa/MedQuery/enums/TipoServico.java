package br.edu.fesa.MedQuery.enums;

public enum TipoServico {

    EXAME("exame"),
    CONSULTA("consulta");

    private String servico;

    TipoServico (String servico){
        this.servico = servico;
    }

    public String getServico(){
        return servico;
    }
}
