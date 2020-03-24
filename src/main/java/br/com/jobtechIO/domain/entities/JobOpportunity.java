package br.com.jobtechIO.domain.entities;

import java.util.List;

import javax.persistence.*;

import br.com.jobtechIO.domain.enumerations.ContractEnum;
import br.com.jobtechIO.domain.enumerations.ExperienceEnum;
import br.com.jobtechIO.domain.enumerations.JobOpportunityStatusEnum;
import br.com.jobtechIO.domain.enumerations.YesNoPartial;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class JobOpportunity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "idcompany", nullable = false)
	private Company company;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, length = 250)
	private String description;

	@Column(nullable = false, length = 250)
	@Enumerated(EnumType.STRING)
	private JobOpportunityStatusEnum status;

	@Column(nullable = false, length = 250)
	private Double minimumWage;

	@Column(nullable = false, length = 250)
	private String benefits;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "jobOpportunityDeficiency", joinColumns = {
			@JoinColumn(name = "jobOpportunityId") }, inverseJoinColumns = { @JoinColumn(name = "deficiencyId") })
	private List<Deficiency> deficiencies;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "jobOpportunitySkill", joinColumns = {
			@JoinColumn(name = "jobOpportunityId") }, inverseJoinColumns = { @JoinColumn(name = "skillId") })
	private List<Skill> skills;

	@Column(nullable = false, length = 250)
	private String office;

	@Column(nullable = false, length = 250)
	private String location;

	@Column(nullable = false, length = 7)
	@Enumerated(EnumType.STRING)
	private YesNoPartial remote;

	@Column(nullable = false, length = 7)
	@Enumerated(EnumType.STRING)
	private ContractEnum typeOfContract;

	@Column(nullable = false, length = 14)
	@Enumerated(EnumType.STRING)
	private ExperienceEnum seniority;

}