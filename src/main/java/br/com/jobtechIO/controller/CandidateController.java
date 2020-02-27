package br.com.jobtechIO.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jobtechIO.domain.dto.request.CandidateRequest;
import br.com.jobtechIO.domain.dto.response.CandidateResponse;
import br.com.jobtechIO.domain.dto.response.JobApplicationResponse;
import br.com.jobtechIO.domain.entities.Candidate;
import br.com.jobtechIO.domain.mapper.CandidateMapper;
import br.com.jobtechIO.domain.mapper.JobApplicationMapper;
import br.com.jobtechIO.service.CandidateService;
import br.com.jobtechIO.service.JobAplicationService;

@RestController
@RequestMapping("/users")
public class CandidateController {

    private final CandidateService service;
    private final CandidateMapper mapper;
    private final JobAplicationService jobAplicationService;
    private final JobApplicationMapper jobApplicationMapper;

    public CandidateController(CandidateService service, CandidateMapper mapper,JobAplicationService jobAplicationService, JobApplicationMapper jobApplicationMapper) {
        this.service = service;
        this.mapper = mapper;
        this.jobAplicationService = jobAplicationService;
        this.jobApplicationMapper = jobApplicationMapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CandidateResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.entityToDto(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAll() {
        return ResponseEntity
                .ok(service.listAllCandidates().stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
    }

    @GetMapping(value = "filtered/{name}")
    public ResponseEntity<List<CandidateResponse>> filterByName(@Valid @PathVariable String name) {
        return ResponseEntity
                .ok(service.listByName(name).stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
    }

    @GetMapping(value = "filtered/jobAplication/{name}")
    public ResponseEntity<List<JobApplicationResponse>> filterJobAplicationsByName(@Valid @PathVariable String name) {
        return ResponseEntity
                .ok(jobAplicationService.listJobApplicationsCandidateName(name).stream().map(x -> jobApplicationMapper.entityToDto(x)).collect(Collectors.toList()));
    }

    @GetMapping(value = "filtered/jobAplication/{cpf}")
    public ResponseEntity<List<JobApplicationResponse>> filterJobAplicationsByCPF(@Valid @PathVariable String cpf) {
        return ResponseEntity
                .ok(jobAplicationService.listJobApplicationsCandidateName(cpf).stream().map(x -> jobApplicationMapper.entityToDto(x)).collect(Collectors.toList()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CandidateResponse> post(@Valid @RequestBody CandidateRequest model,
            UriComponentsBuilder uriComponentsBuilder) {

    	Candidate entity = service.create(mapper.DtoToEntity(model));

        URI uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(entity.getId()).toUri();

        return ResponseEntity.created(uri).body(mapper.entityToDto(entity));

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CandidateResponse> put(@Valid @RequestBody CandidateRequest model,
            @PathVariable Integer id) {

        return ResponseEntity.ok(mapper.entityToDto(service.update(mapper.DtoToEntity(model), id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.ok().build();

    }
}