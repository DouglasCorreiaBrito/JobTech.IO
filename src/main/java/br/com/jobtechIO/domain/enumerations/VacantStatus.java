package br.com.jobtechIO.domain.enumerations;

public enum VacantStatus {

	APPLIED(1), CANCELED(2);

	private final int value;

	VacantStatus(int valueOption) {
		value = valueOption;
	}

	public int getValor() {
		return value;
	}
}