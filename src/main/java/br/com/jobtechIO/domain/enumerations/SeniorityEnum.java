package br.com.jobtechIO.domain.enumerations;

public enum SeniorityEnum {

    JOVEM_APRENDIZ(1), ESTAGIARIO(2), ASSISTENTE(3), TRAINEE(4), JUNIOR(5), PLENO(6), SENIOR(7), ESPECIALISTA(8);

    private final int value;

    SeniorityEnum(int valueOption) {
        value = valueOption;
    }

    public int getValor() {
        return value;
    }
}