package br.com.jobtechIO.domain.enumerations;

public enum JobOpportunityStatusEnum {

	OPEN("OPEN"), CLOSED("CLOSED");

	private final String value;

	JobOpportunityStatusEnum(String valueOption) {
		value = valueOption;
	}

	public String getValor() {
		return value;
	}
}