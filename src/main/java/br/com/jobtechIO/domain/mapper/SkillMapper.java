package br.com.jobtechIO.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jobtechIO.domain.dto.request.SkillRequest;
import br.com.jobtechIO.domain.dto.response.SkillResponse;
import br.com.jobtechIO.domain.entities.Skill;

@Component
public class SkillMapper {

	private final ModelMapper mapper;

	@Autowired
	public SkillMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}

	public SkillResponse entityToDto(Skill entity) {
		return mapper.map(entity, SkillResponse.class);
	}

	public Skill DtoToEntity(SkillRequest request) {
		return mapper.map(request, Skill.class);
	}
}