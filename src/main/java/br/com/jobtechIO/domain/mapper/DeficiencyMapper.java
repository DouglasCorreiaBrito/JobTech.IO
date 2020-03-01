package br.com.jobtechIO.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jobtechIO.domain.dto.request.DeficiencyCreateRequest;
import br.com.jobtechIO.domain.dto.response.DeficiencyResponse;
import br.com.jobtechIO.domain.entities.Deficiency;

@Component
public class DeficiencyMapper {

	private final ModelMapper mapper;

	@Autowired
	public DeficiencyMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}

	public DeficiencyResponse entityToDto(Deficiency entity) {
		return mapper.map(entity, DeficiencyResponse.class);
	}

	public Deficiency DtoToEntity(DeficiencyCreateRequest request) {
		return mapper.map(request, Deficiency.class);
	}
}