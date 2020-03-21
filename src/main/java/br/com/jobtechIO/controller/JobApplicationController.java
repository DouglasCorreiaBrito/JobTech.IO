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

import br.com.jobtechIO.domain.dto.request.JobApplicationRequest;
import br.com.jobtechIO.domain.dto.response.JobApplicationResponse;
import br.com.jobtechIO.domain.entities.JobApplication;
import br.com.jobtechIO.domain.mapper.JobApplicationMapper;
import br.com.jobtechIO.service.JobApplicationService;

@RestController
@RequestMapping("/job-application")
public class JobApplicationController {

	private final JobApplicationService service;
	private final JobApplicationMapper mapper;

	public JobApplicationController(JobApplicationService service, JobApplicationMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<JobApplicationResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.entityToDto(service.getById(id)));
	}

	@GetMapping
	public ResponseEntity<List<JobApplicationResponse>> getAll() {
		return ResponseEntity.ok(
				service.listAllJobApplications().stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}

	@GetMapping(value = "find-candidate/candidate-name/{name}")
	public ResponseEntity<List<JobApplicationResponse>> filterJobApplicationsByName(@Valid @PathVariable String name) {
		return ResponseEntity.ok(service.listJobApplicationsCandidateName(name).stream()
				.map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}

	@GetMapping(value = "find-candidate/candidate-cpf/{cpf}")
	public ResponseEntity<List<JobApplicationResponse>> filterJobApplicationsByCPF(@Valid @PathVariable String cpf) {
		return ResponseEntity.ok(service.listJobApplicationsCandidateName(cpf).stream()
				.map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<JobApplicationResponse> post(@Valid @RequestBody JobApplicationRequest model,
			UriComponentsBuilder uriComponentsBuilder) {

		JobApplication entity = service.create(mapper.requestToEntity(model));

		URI uri = uriComponentsBuilder.path("/job-application/{id}").buildAndExpand(entity.getId()).toUri();

		return ResponseEntity.created(uri).body(mapper.entityToDto(entity));

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<JobApplicationResponse> put(@Valid @RequestBody JobApplicationRequest model,
			@PathVariable Integer id) {

		return ResponseEntity.ok(mapper.entityToDto(service.update(mapper.requestToEntity(model), id)));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		service.delete(id);

		return ResponseEntity.ok().build();

	}
}