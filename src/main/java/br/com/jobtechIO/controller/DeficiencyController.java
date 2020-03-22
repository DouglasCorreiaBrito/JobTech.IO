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

import br.com.jobtechIO.domain.dto.request.DeficiencyCreateRequest;
import br.com.jobtechIO.domain.dto.response.DeficiencyResponse;
import br.com.jobtechIO.domain.entities.Deficiency;
import br.com.jobtechIO.domain.mapper.DeficiencyMapper;
import br.com.jobtechIO.service.DeficiencyService;

@RestController
@RequestMapping("/deficiency")
@Api(tags = { " Deficiencies " }, value = "end-point to manage deficiencies")
public class DeficiencyController {

	private final DeficiencyService service;
	private final DeficiencyMapper mapper;

	public DeficiencyController(DeficiencyService service, DeficiencyMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@ApiOperation(value = "filter deficiency by id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<DeficiencyResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.entityToDto(service.getById(id)));
	}

	@ApiOperation(value = "list all deficiencies")
	@GetMapping
	public ResponseEntity<List<DeficiencyResponse>> getAll() {
		return ResponseEntity.ok(
				service.listAllDeficiencies().stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}

	@ApiOperation(value = "filter deficiencies by description")
	@GetMapping(value = "filtered/{description}")
	public ResponseEntity<List<DeficiencyResponse>> filterByDescription(@Valid @PathVariable String description) {
		return ResponseEntity.ok(service.listByDescription(description).stream().map(x -> mapper.entityToDto(x))
				.collect(Collectors.toList()));
	}

	@ApiOperation(value = "create deficiency")
	@PostMapping
	@Transactional
	public ResponseEntity<DeficiencyResponse> post(@Valid @RequestBody DeficiencyCreateRequest model,
			UriComponentsBuilder uriComponentsBuilder) {

		Deficiency entity = service.create(mapper.DtoToEntity(model));

		URI uri = uriComponentsBuilder.path("/deficiency/{id}").buildAndExpand(entity.getId()).toUri();

		return ResponseEntity.created(uri).body(mapper.entityToDto(entity));

	}

	@ApiOperation(value = "update deficiency")
	@PutMapping(value = "/{id}")
	public ResponseEntity<DeficiencyResponse> put(@Valid @RequestBody DeficiencyCreateRequest model,
			@PathVariable Integer id) {

		return ResponseEntity.ok(mapper.entityToDto(service.update(mapper.DtoToEntity(model), id)));
	}

	@ApiOperation(value = "delete deficiency")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		service.delete(id);

		return ResponseEntity.ok().build();

	}
}