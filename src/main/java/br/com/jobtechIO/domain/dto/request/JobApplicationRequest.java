package br.com.jobtechIO.domain.dto.request;

import javax.validation.constraints.NotNull;

import br.com.jobtechIO.domain.enumerations.VacantStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobApplicationRequest {

	@NotNull(message = "jobOpportunity is required")
	public Integer idJobOpportunity;

	@NotNull(message = "candidate is required")
	public Integer idCandidate;

	@NotNull(message = "status is required")
	private VacantStatus status;

}