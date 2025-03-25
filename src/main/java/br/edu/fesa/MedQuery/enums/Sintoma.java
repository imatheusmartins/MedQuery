package br.edu.fesa.MedQuery.enums;

public enum Sintoma {
    DOR_NO_PEITO("Dor no peito"),
    FADIGA("Fadiga"),
    TOSSE("Tosse"),
    FEBRE("Febre"),
    DOR_ABDOMINAL("Dor abdominal"),
    MANCHAS_NA_PELE("Manchas na pele"),
    ANSIEDADE("Ansiedade"),
    PERDA_DE_VISÃO("Perda de visão"),
    DOR_NAS_ARTICULACOES("Dor nas articulações"),
    ALTERACOES_HORMONAIS("Alterações hormonais");
    
    private String sintoma;

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    private Sintoma(String sintoma){
        this.sintoma = sintoma;
    }

    
}