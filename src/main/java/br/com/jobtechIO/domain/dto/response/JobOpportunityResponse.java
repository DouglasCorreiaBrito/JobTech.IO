package br.com.jobtechIO.domain.dto.response;

import java.util.List;

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
public class JobOpportunityResponse {

    private Integer id;

    private Company company;

    private String title;

    private String description;
    
    private JobOpportunityStatusEnum status;

    private Double minimumWage;

    private String benefits;

    private List<Deficiency> deficiencies;

    private List<Skill> skills;

    private String office;

    private String location;

    private YesNoPartial remote;

    private ContractEnum typeOfContract;

    private ExperienceEnum seniority;

}