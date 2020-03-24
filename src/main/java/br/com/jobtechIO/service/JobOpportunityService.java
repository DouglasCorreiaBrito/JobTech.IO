package br.com.jobtechIO.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.jobtechIO.domain.enumerations.JobOpportunityStatusEnum;
import br.com.jobtechIO.domain.enumerations.VacantStatus;
import br.com.jobtechIO.exceptions.GenericBadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jobtechIO.domain.entities.JobOpportunity;
import br.com.jobtechIO.exceptions.GenericNotFoundException;
import br.com.jobtechIO.repository.JobOpportunityRepository;

@Service
public class JobOpportunityService {

	private final JobOpportunityRepository repository;

	@Autowired
	public JobOpportunityService(JobOpportunityRepository repository) {
		this.repository = repository;
	}

	public List<JobOpportunity> listAllJobOpportunities() {
		return repository.findAll();
	}

	public JobOpportunity getById(Integer id) {
		Optional<JobOpportunity> cOptional = repository.findById(id);
		return cOptional.orElseThrow(() -> new GenericNotFoundException("Job Opportunity not found"));
	}

	public List<JobOpportunity> listByTitle(String title) {
		return repository.findByTitleContainingIgnoreCase(title);
	}

	public JobOpportunity create(JobOpportunity entity){
		if (entity.getStatus() == JobOpportunityStatusEnum.OPEN){
			entity.setCreatedAt(LocalDate.now());
			entity.setUpdatedAt(LocalDate.now());
			return repository.save(entity);
		}
		throw new GenericBadRequestException("Invalid status. Expected Open");

	}

	public void delete(Integer id) {
		repository.delete(getById(id));
	}

	public JobOpportunity update(JobOpportunity entity, Integer id) {

		JobOpportunity jobOpportunity = getById(id);

		jobOpportunity.setId(id);
		jobOpportunity.setCompany(entity.getCompany());
		jobOpportunity.setTitle(entity.getTitle());
		jobOpportunity.setDescription(entity.getDescription());
		jobOpportunity.setStatus(entity.getStatus());
		jobOpportunity.setMinimumWage(entity.getMinimumWage());
		jobOpportunity.setBenefits(entity.getBenefits());
		jobOpportunity.setDeficiencies(entity.getDeficiencies());
		jobOpportunity.setSkills(entity.getSkills());
		jobOpportunity.setOffice(entity.getOffice());
		jobOpportunity.setLocation(entity.getLocation());
		jobOpportunity.setTypeOfContract(entity.getTypeOfContract());
		jobOpportunity.setSeniority(entity.getSeniority());
		jobOpportunity.setUpdatedAt(LocalDate.now());

		return repository.save(jobOpportunity);
	}

	public List<JobOpportunity> listByContract(String contract) {
		return repository.findByTypeOfContract(contract);
	}
}