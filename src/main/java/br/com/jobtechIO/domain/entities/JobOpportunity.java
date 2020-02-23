package br.com.jobtechIO.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.jobtechIO.domain.enumerations.SeniorityEnum;
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

    @Column(nullable = false, length = 250)
    private String description;

    @Column(nullable = false, length = 250)
    private Double minimumWage;

    @Column(nullable = false, length = 250)
    private String benefits;

    @Transient
    private List<Deficiency> deficiencyList;

    @Transient
    private List<Skills> skillsList;

    @Column(nullable = false, length = 250)
    private String office;

    @Column(nullable = false, length = 250)
    private String location;

    @Column
    private Boolean remote;

    @Column(nullable = false, length = 250)
    private String typeOfContract;

   @Enumerated(EnumType.STRING)
    private SeniorityEnum seniority;

}