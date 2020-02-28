package br.com.jobtechIO.domain.dto.response;

import br.com.jobtechIO.domain.entities.Candidate;
import br.com.jobtechIO.domain.entities.JobOpportunity;
import br.com.jobtechIO.domain.enumerations.VacantStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobApplicationResponse {

    public Integer id;

    public JobOpportunity jobOpportunity;
    
    public Candidate candidate;

    private VacantStatus status;

}