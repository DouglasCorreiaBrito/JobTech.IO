package br.com.jobtechIO.domain.dto.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.jobtechIO.domain.entities.Skill;
import br.com.jobtechIO.domain.validators.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateRequest {

	@NotNull
	@NotBlank(message = "name is required")
	private String name;

	@CPF
	@NotNull
	private String CPF;

	@Email
	@NotNull
	private String email;

	@NotNull
	@Phone
	private String telephone;

	@NotNull
	@NotBlank(message = "endereco is required")
	private String endereco;

	@NotNull
	@DateTimeFormat
	private LocalDate birthdate;

	private List<Skill> skills;

}