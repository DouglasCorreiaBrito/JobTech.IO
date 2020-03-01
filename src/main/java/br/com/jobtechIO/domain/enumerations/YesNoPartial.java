package br.com.jobtechIO.domain.enumerations;

public enum YesNoPartial {

	YES("YES"), NO("NO"), PARTIAL("PARTIAL");

	private final String value;

	YesNoPartial(String valueOption) {
		value = valueOption;
	}

	public String getValor() {
		return value;
	}
}