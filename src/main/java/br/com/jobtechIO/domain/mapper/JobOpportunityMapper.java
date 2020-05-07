package br.com.jobtechIO.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jobtechIO.domain.dto.request.JobOpportunityRequest;
import br.com.jobtechIO.domain.dto.response.JobOpportunityResponse;
import br.com.jobtechIO.domain.entities.Deficiency;
import br.com.jobtechIO.domain.entities.JobOpportunity;
import br.com.jobtechIO.domain.entities.Skill;
import br.com.jobtechIO.service.CompanyService;
import br.com.jobtechIO.service.DeficiencyService;
import br.com.jobtechIO.service.SkillService;

@Component
public class JobOpportunityMapper {

	private final ModelMapper mapper;
	
	private final CompanyService companyService;
	
	private final SkillService skillService;
	
	private final DeficiencyService deficiencyService;

	@Autowired
	public JobOpportunityMapper(ModelMapper mapper, CompanyService companyService, SkillService skillService, DeficiencyService deficiencyService) {
		this.mapper = mapper;
		this.companyService = companyService;
		this.skillService = skillService;
		this.deficiencyService = deficiencyService;
	}

	public JobOpportunityResponse entityToDto(JobOpportunity entity) {
		return mapper.map(entity, JobOpportunityResponse.class);
	}

	public JobOpportunity DtoToEntity(JobOpportunityRequest request) {
		return mapper.map(request, JobOpportunity.class);
	}
	
	public JobOpportunity requestToEntity(JobOpportunityRequest request) {
		JobOpportunity jobOpportunity =  mapper.map(request, JobOpportunity.class);
		
		jobOpportunity.setCompany(companyService.getById(request.getCompanyId()));
		jobOpportunity.setSkills(getSkillsModels(request.getSkills()));
		jobOpportunity.setDeficiencies(getDeficienciesModels(request.getDeficiencies()));
		
		return jobOpportunity;
	}

	private List<Skill> getSkillsModels(List<Integer> skillsIds) {
		List<Skill> skills = new ArrayList<Skill>();

		skillsIds.forEach(skillId -> {
			skills.add(skillService.getById(skillId));
		});

		return skills;
	}
	
	private List<Deficiency> getDeficienciesModels(List<Integer> deficienciesIds) {
		if(deficienciesIds != null){

			List<Deficiency> deficiencies = new ArrayList<Deficiency>();

			deficienciesIds.forEach(deficiencyId -> {
				deficiencies.add(deficiencyService.getById(deficiencyId));
			});

			return deficiencies;
		}
		return new ArrayList<Deficiency>();
	}
}