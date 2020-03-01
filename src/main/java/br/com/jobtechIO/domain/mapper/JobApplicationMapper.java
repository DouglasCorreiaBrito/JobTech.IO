package br.com.jobtechIO.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jobtechIO.domain.dto.request.JobApplicationRequest;
import br.com.jobtechIO.domain.dto.response.JobApplicationResponse;
import br.com.jobtechIO.domain.entities.Candidate;
import br.com.jobtechIO.domain.entities.JobApplication;
import br.com.jobtechIO.domain.entities.JobOpportunity;
import br.com.jobtechIO.service.CandidateService;
import br.com.jobtechIO.service.JobOpportunityService;

@Component
public class JobApplicationMapper {

	private final ModelMapper mapper;
	private final CandidateService candidateService;
	private final JobOpportunityService jobOportunityService;

	@Autowired
	public JobApplicationMapper(ModelMapper mapper, CandidateService candidateService,
			JobOpportunityService jobOportunityService) {
		this.mapper = mapper;
		this.candidateService = candidateService;
		this.jobOportunityService = jobOportunityService;
	}

	public JobApplicationResponse entityToDto(JobApplication entity) {

		return mapper.map(entity, JobApplicationResponse.class);
	}

	public JobApplication dtoToEntity(JobApplicationResponse request) {
		return mapper.map(request, JobApplication.class);
	}

	public JobApplication requestToEntity(JobApplicationRequest request) {
		JobApplication jobApplication = mapper.map(request, JobApplication.class);

		JobOpportunity jobOpportunity = jobOportunityService.getById(request.getIdJobOpportunity());
		Candidate candidate = candidateService.getById(request.getIdCandidate());

		jobApplication.setCandidate(candidate);
		jobApplication.setJobOpportunity(jobOpportunity);

		return jobApplication;
	}
}