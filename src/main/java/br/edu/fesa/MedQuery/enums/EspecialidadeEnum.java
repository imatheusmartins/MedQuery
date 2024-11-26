package br.edu.fesa.MedQuery.enums;

public enum EspecialidadeEnum {
    CARDIOLOGIA("Cardiologia"),
    CLINICA_GERAL("Clínica Geral"),
    PEDIATRIA("Pediatria"),
    GINECOLOGIA("Ginecologia e Obstetrícia"),
    ORTOPEDIA("Ortopedia"),
    DERMATOLOGIA("Dermatologia"),
    PSIQUIATRIA("Psiquiatria"),
    OFTALMOLOGIA("Oftalmologia"),
    ENDOCRINOLOGIA("Endocrinologia"),
    NEUROLOGIA("Neurologia");
    

    private String especialidade;

    private EspecialidadeEnum(String especialidade){
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}