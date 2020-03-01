package br.com.jobtechIO.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jobtechIO.domain.dto.request.CandidateRequest;
import br.com.jobtechIO.domain.dto.response.CandidateResponse;
import br.com.jobtechIO.domain.entities.Candidate;
import br.com.jobtechIO.domain.entities.Skill;
import br.com.jobtechIO.service.SkillService;

@Component
public class CandidateMapper {

	private final ModelMapper mapper;

	private final SkillService skillService;
	
	@Autowired
	public CandidateMapper(ModelMapper mapper, SkillService skillService) {
		this.mapper = mapper;
		this.skillService = skillService;
	}

	public CandidateResponse entityToDto(Candidate entity) {
		return mapper.map(entity, CandidateResponse.class);
	}

	public Candidate DtoToEntity(CandidateRequest request) {
		return mapper.map(request, Candidate.class);
	}
	
	public Candidate requestToCandidate(CandidateRequest request) {
		Candidate candidate = mapper.map(request, Candidate.class);
		candidate.setSkills(getSkillsModels(request.getSkills()));
		return candidate;
	}

	private List<Skill> getSkillsModels(List<Integer> skillsIds) {
		List<Skill> skills = new ArrayList<Skill>();

		skillsIds.forEach(skillId -> {
			skills.add(skillService.getById(skillId));
		});

		return skills;
	}
}