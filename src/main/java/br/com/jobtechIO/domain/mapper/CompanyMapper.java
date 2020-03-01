package br.com.jobtechIO.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jobtechIO.domain.dto.request.CompanyRequest;
import br.com.jobtechIO.domain.dto.response.CompanyResponse;
import br.com.jobtechIO.domain.entities.Company;

@Component
public class CompanyMapper {

	private final ModelMapper mapper;

	@Autowired
	public CompanyMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}

	public CompanyResponse entityToDto(Company entity) {
		return mapper.map(entity, CompanyResponse.class);
	}

	public Company DtoToEntity(CompanyRequest request) {
		return mapper.map(request, Company.class);
	}
}