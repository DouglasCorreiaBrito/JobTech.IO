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

import br.com.jobtechIO.domain.dto.request.SkillRequest;
import br.com.jobtechIO.domain.dto.response.SkillResponse;
import br.com.jobtechIO.domain.entities.Skill;
import br.com.jobtechIO.domain.mapper.SkillMapper;
import br.com.jobtechIO.service.SkillService;

@RestController
@RequestMapping("/skills")
@Api(tags = { " Skils " }, value = "end-point to manage job skills")
public class SkillController {

	private final SkillService service;
	private final SkillMapper mapper;

	public SkillController(SkillService service, SkillMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@ApiOperation(value = "filter skill by id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<SkillResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.entityToDto(service.getById(id)));
	}

	@ApiOperation(value = "list all skills")
	@GetMapping
	public ResponseEntity<List<SkillResponse>> getAll() {
		return ResponseEntity
				.ok(service.listAllSkills().stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
	}

	@ApiOperation(value = "filter skills by description")
	@GetMapping(value = "filtered/{description}")
	public ResponseEntity<List<SkillResponse>> filterByDescription(@Valid @PathVariable String description) {
		return ResponseEntity.ok(service.listByDescription(description).stream().map(x -> mapper.entityToDto(x))
				.collect(Collectors.toList()));
	}

	@ApiOperation(value = "create skill")
	@PostMapping
	@Transactional
	public ResponseEntity<SkillResponse> post(@Valid @RequestBody SkillRequest model,
			UriComponentsBuilder uriComponentsBuilder) {

		Skill entity = service.create(mapper.DtoToEntity(model));

		URI uri = uriComponentsBuilder.path("/skills/{id}").buildAndExpand(entity.getId()).toUri();

		return ResponseEntity.created(uri).body(mapper.entityToDto(entity));

	}

	@ApiOperation(value = "update skill")
	@PutMapping(value = "/{id}")
	public ResponseEntity<SkillResponse> put(@Valid @RequestBody SkillRequest model, @PathVariable Integer id) {

		return ResponseEntity.ok(mapper.entityToDto(service.update(mapper.DtoToEntity(model), id)));
	}

	@ApiOperation(value = "delete skill")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		service.delete(id);

		return ResponseEntity.ok().build();

	}
}