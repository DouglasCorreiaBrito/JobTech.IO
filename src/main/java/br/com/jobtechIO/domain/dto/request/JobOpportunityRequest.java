package br.com.jobtechIO.domain.dto.request;

import java.util.List;

import br.com.jobtechIO.domain.entities.Company;
import br.com.jobtechIO.domain.entities.Deficiency;
import br.com.jobtechIO.domain.entities.Skill;
import br.com.jobtechIO.domain.enumerations.ExperienceEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobOpportunityRequest {
    
    private Integer id;
    
    private Company company;
    
    private String title;
    
    //TODO if description will be an HTML template, add a HTML parser to validate data integrity
    private String description;

    private Double minimumWage;

    private String benefits;

    private List<Deficiency> deficiencies;

    private List<Skill> skills;

    private String office;

    private String location;

    private Boolean remote;

    private String typeOfContract;

    private ExperienceEnum seniority;
    
}