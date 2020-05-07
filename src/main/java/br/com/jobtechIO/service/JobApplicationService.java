package br.com.jobtechIO.service;

import br.com.jobtechIO.domain.entities.JobApplication;
import br.com.jobtechIO.domain.enumerations.VacantStatus;
import br.com.jobtechIO.exceptions.GenericBadRequestException;
import br.com.jobtechIO.exceptions.GenericNotFoundException;
import br.com.jobtechIO.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

	private final JobApplicationRepository repository;

	@Autowired
	public JobApplicationService(JobApplicationRepository repository) {
		this.repository = repository;
	}

	public List<JobApplication> listAllJobApplications() {
		return repository.findAll();
	}

	public JobApplication getById(Integer id) {
		Optional<JobApplication> cOptional = repository.findById(id);
		return cOptional.orElseThrow(() -> new GenericNotFoundException("Job Aplication not found"));
	}

	public List<JobApplication> listJobOportunity(Integer idOportunity) {

		return repository.findByJobOpportunity_id(idOportunity);
	}

	public List<JobApplication> listApplicationsByCompany(Integer idCompany) {
		return repository.findByJobOpportunity_Company_id(idCompany);
	}

	public List<JobApplication> listJobApplicationsCandidateName(String name) {
		return repository.findByCandidate_name(name);
	}

	public List<JobApplication> listJobApplicationsCandidateCPF(String CPF) {
		return repository.findByCandidate_CPF(CPF);
	}

	public List<JobApplication> listJobApplicationsCandidateId(Integer id) {
		return repository.findByCandidate_id(id);
	}

	public JobApplication create(JobApplication entity) {

		if (entity.getStatus() == VacantStatus.APPLIED){

			entity.setCreatedAt(LocalDate.now());
			entity.setUpdatedAt(LocalDate.now());
			return repository.save(entity);
		}
		throw new GenericBadRequestException("Invalid status. Expected APPLIED");
	}

	public void delete(Integer id) {
		repository.delete(getById(id));
	}

	public JobApplication update(JobApplication entity, Integer id) {

		JobApplication jobApplication = getById(id);

		jobApplication.setCandidate(entity.getCandidate());
		jobApplication.setJobOpportunity(entity.getJobOpportunity());
		jobApplication.setStatus(entity.getStatus());
		jobApplication.setId(id);
		jobApplication.setUpdatedAt(LocalDate.now());

		return repository.save(jobApplication);
	}
}