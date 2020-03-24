package br.com.jobtechIO.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = { " Opportunities " }, value = "end-point to manage job opportunities")
public class JobOpportunityController {

	private final JobOpportunityService service;
	private final JobOpportunityMapper mapper;

	public JobOpportunityController(JobOpportunityService service, JobOpportunityMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@ApiOperation(value = "filter opportunity by id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<JobOpportunityResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.entityToDto(service.getById(id)));
	}

	@ApiOperation(value = "list all opportunities")
	@GetMapping
	public ResponseEntity<List<JobOpportunityResponse>> getAll() {
		return ResponseEntity.ok(service.listAllJobOpportunities().stream().map(x -> mapper.entityToDto(x))
				.collect(Collectors.toList()));
	}

	@ApiOperation(value = "filter opportunity by title")
	@GetMapping(value = "filtered/{title}")
	public ResponseEntity<List<JobOpportunityResponse>> filterByTitle(@Valid @PathVariable String title) {
		return ResponseEntity
				.ok(service.listByTitle(title).stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}

	@ApiOperation(value = "filter opportunity by contract")
	@GetMapping(value = "filtered/contract/{contract}")
	public ResponseEntity<List<JobOpportunityResponse>> filterByContract(@Valid @PathVariable String contract) {
		return ResponseEntity
				.ok(service.listByContract(contract).stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}

	@ApiOperation(value = "create opportunity")
	@PostMapping
	@Transactional
	public ResponseEntity<JobOpportunityResponse> post(@Valid @RequestBody JobOpportunityRequest model,
			UriComponentsBuilder uriComponentsBuilder)  {

		JobOpportunity entity = service.create(mapper.requestToEntity(model));

		URI uri = uriComponentsBuilder.path("/jobs/{id}").buildAndExpand(entity.getId()).toUri();

		return ResponseEntity.created(uri).body(mapper.entityToDto(entity));

	}

	@ApiOperation(value = "update opportunity")
	@PutMapping(value = "/{id}")
	public ResponseEntity<JobOpportunityResponse> put(@Valid @RequestBody JobOpportunityRequest model,
			@PathVariable Integer id) {

		return ResponseEntity.ok(mapper.entityToDto(service.update(mapper.requestToEntity(model), id)));
	}

	@ApiOperation(value = "delete opportunity")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}