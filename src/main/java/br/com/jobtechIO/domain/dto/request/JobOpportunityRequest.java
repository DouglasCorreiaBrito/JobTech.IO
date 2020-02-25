package br.com.jobtechIO.domain.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jobtechIO.domain.entities.Company;
import br.com.jobtechIO.domain.entities.Deficiency;
import br.com.jobtechIO.domain.entities.Skill;
import br.com.jobtechIO.domain.enumerations.ContractEnum;
import br.com.jobtechIO.domain.enumerations.ExperienceEnum;
import br.com.jobtechIO.domain.enumerations.JobOpportunityStatusEnum;
import br.com.jobtechIO.domain.enumerations.YesNoPartial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobOpportunityRequest {

	@NotNull
	private Company company;

	@NotEmpty(message = "title is required")
	@NotBlank(message = "title is not blank")
	@Size(max = 200)
	private String title;

	// TODO if description will be an HTML template, add a HTML parser to validate data integrity
	@NotEmpty(message = "description is required")
	private String description;

	@NotNull
	private JobOpportunityStatusEnum status;

	@NotNull
	private Double minimumWage;

	@NotNull
	private String benefits;

	private List<Deficiency> deficiencies;

	private List<Skill> skills;

	@NotEmpty(message = "office is required")
	@Size(max = 200)
	private String office;

	@NotEmpty(message = "location is required")
	@Size(max = 200)
	private String location;

	@NotNull
	private YesNoPartial remote;

	@NotNull
	private ContractEnum typeOfContract;

	@NotNull
	private ExperienceEnum seniority;

}