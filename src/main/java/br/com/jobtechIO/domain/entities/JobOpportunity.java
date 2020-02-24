package br.com.jobtechIO.domain.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.jobtechIO.domain.enumerations.ExperienceEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobOpportunity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "idcompany", nullable = false)
    private Company company;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 250)
    private String description;

    @Column(nullable = false, length = 250)
    private Double minimumWage;

    @Column(nullable = false, length = 250)
    private String benefits;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.MERGE
            })
    @JoinTable(name = "jobOpportunityDeficiency",
            joinColumns = { @JoinColumn(name = "jobOpportunityId") },
            inverseJoinColumns = { @JoinColumn(name = "deficiencyId") })
    private List<Deficiency> deficiencies;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.MERGE
            })
    @JoinTable(name = "jobOpportunitySkill",
            joinColumns = { @JoinColumn(name = "jobOpportunityId") },
            inverseJoinColumns = { @JoinColumn(name = "skillId") })
    private List<Skill> skills;

    @Column(nullable = false, length = 250)
    private String office;

    @Column(nullable = false, length = 250)
    private String location;

    @Column
    private Boolean remote;

    @Column(nullable = false, length = 250)
    private String typeOfContract;

   @Enumerated(EnumType.STRING)
    private ExperienceEnum seniority;

}