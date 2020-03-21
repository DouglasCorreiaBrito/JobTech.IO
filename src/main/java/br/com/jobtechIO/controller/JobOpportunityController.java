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

import br.com.jobtechIO.domain.dto.request.JobOpportunityRequest;
import br.com.jobtechIO.domain.dto.response.JobOpportunityResponse;
import br.com.jobtechIO.domain.entities.JobOpportunity;
import br.com.jobtechIO.domain.mapper.JobOpportunityMapper;
import br.com.jobtechIO.service.JobOpportunityService;

@RestController
@RequestMapping("/jobs")
public class JobOpportunityController {

	private final JobOpportunityService service;
	private final JobOpportunityMapper mapper;

	public JobOpportunityController(JobOpportunityService service, JobOpportunityMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<JobOpportunityResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.entityToDto(service.getById(id)));
	}

	@GetMapping
	public ResponseEntity<List<JobOpportunityResponse>> getAll() {
		return ResponseEntity.ok(service.listAllJobOpportunities().stream().map(x -> mapper.entityToDto(x))
				.collect(Collectors.toList()));
	}

	@GetMapping(value = "filtered/{title}")
	public ResponseEntity<List<JobOpportunityResponse>> filterByTitle(@Valid @PathVariable String title) {
		return ResponseEntity
				.ok(service.listByTitle(title).stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<JobOpportunityResponse> post(@Valid @RequestBody JobOpportunityRequest model,
			UriComponentsBuilder uriComponentsBuilder) {

		JobOpportunity entity = service.create(mapper.requestToEntity(model));

		URI uri = uriComponentsBuilder.path("/jobs/{id}").buildAndExpand(entity.getId()).toUri();

		return ResponseEntity.created(uri).body(mapper.entityToDto(entity));

	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<JobOpportunityResponse> put(@Valid @RequestBody JobOpportunityRequest model,
			@PathVariable Integer id) {

		return ResponseEntity.ok(mapper.entityToDto(service.update(mapper.requestToEntity(model), id)));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}