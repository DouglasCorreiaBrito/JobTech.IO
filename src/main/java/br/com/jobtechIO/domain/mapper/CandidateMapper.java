package br.com.jobtechIO.domain.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jobtechIO.domain.dto.request.CandidateRequest;
import br.com.jobtechIO.domain.dto.response.CandidateResponse;
import br.com.jobtechIO.domain.entities.Candidate;

@Component
public class CandidateMapper{

    private final ModelMapper mapper;

    @Autowired
    public CandidateMapper(ModelMapper mapper){
        this.mapper = mapper;
    }

    public CandidateResponse entityToDto(Candidate entity){
        return mapper.map(entity, CandidateResponse.class);
    }

    public Candidate DtoToEntity(CandidateRequest request){
        return mapper.map(request, Candidate.class);
    }
}