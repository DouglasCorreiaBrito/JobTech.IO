package br.com.jobtechIO.domain.enumerations;

public enum ContractEnum {

	CLT("CLT"), CONTRATO_PJ("PJ"), CONTRATO_ESTAGIO("ESTAGIO");

	private final String value;

	ContractEnum(String valueOption) {
		value = valueOption;
	}

	public String getValor() {
		return value;
	}
}