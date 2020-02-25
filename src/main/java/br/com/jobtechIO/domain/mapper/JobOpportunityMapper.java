package br.com.jobtechIO.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jobtechIO.domain.dto.request.JobOpportunityRequest;
import br.com.jobtechIO.domain.dto.response.JobOpportunityResponse;
import br.com.jobtechIO.domain.entities.JobOpportunity;

@Component
public class JobOpportunityMapper{

    private final ModelMapper mapper;

    @Autowired
    public JobOpportunityMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public JobOpportunityResponse entityToDto(JobOpportunity entity){
        return mapper.map(entity, JobOpportunityResponse.class);
    }

    public JobOpportunity DtoToEntity(JobOpportunityRequest request){
        return mapper.map(request, JobOpportunity.class);
    }
}