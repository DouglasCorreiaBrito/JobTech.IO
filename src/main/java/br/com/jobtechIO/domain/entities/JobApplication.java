package br.com.jobtechIO.domain.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.jobtechIO.domain.enumerations.VacantStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class JobApplication extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "idjobOpportunity", nullable = false)
	public JobOpportunity jobOpportunity;

	@ManyToOne
	@JoinColumn(name = "idCandidate", nullable = false)
	public Candidate candidate;

	@Enumerated(EnumType.STRING)
	private VacantStatus status;

}