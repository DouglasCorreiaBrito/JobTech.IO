package br.com.jobtechIO.domain.dto.response;

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
public class JobOpportunityResponse {

//TODO apply "benefícios" and think about this salary to resolve in a enum

    private Integer id;

    private Company company;

    private String title;

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