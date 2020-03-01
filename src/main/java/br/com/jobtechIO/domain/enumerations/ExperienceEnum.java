package br.com.jobtechIO.domain.enumerations;

public enum ExperienceEnum {

	JOVEM_APRENDIZ("JOVEM_APRENDIZ"), INTERN("INTERN"), ASSISTANT("ASSISTANT"), TRAINEE("TRAINEE"), JUNIOR("JUNIOR"),
	ENGINEER("ENGINEER"), SENIOR("SENIOR"), SPECIALIST("SPECIALIST");

	private final String value;

	ExperienceEnum(String valueOption) {
		value = valueOption;
	}

	public String getValor() {
		return value;
	}
}