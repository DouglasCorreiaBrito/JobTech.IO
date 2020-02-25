package br.com.jobtechIO.domain.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    
    @NotEmpty(message = "company is required")
    @NotBlank(message = "company is not blank")
    private Company company;
    
    @NotEmpty(message = "title is required")
    @NotBlank(message = "title is not blank")
    @Size(max = 200)
    private String title;
    
    //TODO if description will be an HTML template, add a HTML parser to validate data integrity
    @NotEmpty(message = "description is required")
    @NotBlank(message = "description is not blank")
    private String description;
    
    @NotEmpty(message = "status is required")
    @NotBlank(message = "status is not blank")
    private JobOpportunityStatusEnum status;

    @NotEmpty(message = "minimumWage is required")
    @NotBlank(message = "minimumWage is not blank")
    private Double minimumWage;

    private String benefits;

    private List<Deficiency> deficiencies;

    @NotEmpty(message = "skills are required")
    @NotBlank(message = "skills are not blank")
    private List<Skill> skills;

    @NotEmpty(message = "office is required")
    @NotBlank(message = "office is not blank")
    @Size(max = 200)
    private String office;

    @NotEmpty(message = "location is required")
    @NotBlank(message = "location is not blank")
    @Size(max = 200)
    private String location;

    @NotEmpty(message = "remote is required")
    @NotBlank(message = "remote is not blank")
    private YesNoPartial remote;

    @NotEmpty(message = "typeOfContract is required")
    @NotBlank(message = "typeOfContract is not blank")
    private ContractEnum typeOfContract;

    @NotEmpty(message = "seniority is required")
    @NotBlank(message = "seniority is not blank")
    private ExperienceEnum seniority;
    
}