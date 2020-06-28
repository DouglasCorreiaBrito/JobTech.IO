package br.com.jobtechIO.controller;

import br.com.jobtechIO.domain.dto.request.CandidateRequest;
import br.com.jobtechIO.domain.dto.response.CandidateResponse;
import br.com.jobtechIO.domain.dto.response.JobOpportunityResponse;
import br.com.jobtechIO.domain.entities.Candidate;
import br.com.jobtechIO.domain.mapper.CandidateMapper;
import br.com.jobtechIO.service.CandidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidates")
@Api(tags = { " Candidates " }, value = "end-point to manage candidates")
public class CandidateController {

	private final CandidateService service;
	private final CandidateMapper mapper;

	public CandidateController(CandidateService service, CandidateMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@ApiOperation(value = "filter candidate by id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CandidateResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.entityToDto(service.getById(id)));
	}


	@ApiOperation(value = "list all candidates")
	@GetMapping
	public ResponseEntity<List<CandidateResponse>> getAll() {
		return ResponseEntity
				.ok(service.listAllCandidates().stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}

	@ApiOperation(value = "filter candidates by name")
	@GetMapping(value = "filtered/{name}")
	public ResponseEntity<List<CandidateResponse>> filterByName(@Valid @PathVariable String name) {
		return ResponseEntity
				.ok(service.listByName(name).stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}


	@ApiOperation(value = "create a candidate")
	@PostMapping
	@Transactional
	public ResponseEntity<CandidateResponse> post(@Valid @RequestBody CandidateRequest model,
			UriComponentsBuilder uriComponentsBuilder) {

		Candidate entity = service.create(mapper.requestToCandidate(model));

		URI uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(entity.getId()).toUri();

		return ResponseEntity.created(uri).body(mapper.entityToDto(entity));
	}

	@ApiOperation(value = "update a candidate")
	@PutMapping(value = "/{id}")
	public ResponseEntity<CandidateResponse> put(@Valid @RequestBody CandidateRequest model, @PathVariable Integer id) {

		return ResponseEntity.ok(mapper.entityToDto(service.update(mapper.requestToCandidate(model), id)));
	}

	@ApiOperation(value = "delete a candidate")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}