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

import br.com.jobtechIO.domain.dto.request.DeficiencyCreateRequest;
import br.com.jobtechIO.domain.dto.response.DeficiencyResponse;
import br.com.jobtechIO.domain.entities.Deficiency;
import br.com.jobtechIO.domain.mapper.DeficiencyMapper;
import br.com.jobtechIO.service.DeficiencyService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/deficiency")
public class DeficiencyController {

    private final DeficiencyService service;
    private final DeficiencyMapper mapper;

    public DeficiencyController(DeficiencyService service, DeficiencyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeficiencyResponse> getById(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(mapper.entityToDto(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<DeficiencyResponse>> getAll() {
        return ResponseEntity.ok(
                service.listAllDeficiencies().stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{description}")
    public ResponseEntity<List<DeficiencyResponse>> filterByDescription(@Valid @PathVariable String description) {
        return ResponseEntity.ok(service.listByDescription(description).stream().map(x -> mapper.entityToDto(x))
                .collect(Collectors.toList()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DeficiencyResponse> post(@Valid @RequestBody DeficiencyCreateRequest model,
            UriComponentsBuilder uriComponentsBuilder) {

        Deficiency entity = service.create(mapper.DtoToEntity(model));

        URI uri = uriComponentsBuilder.path("/deficiency/{id}").buildAndExpand(entity.getId()).toUri();

        return ResponseEntity.created(uri).body(mapper.entityToDto(entity));

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DeficiencyResponse> put(@Valid @RequestBody DeficiencyCreateRequest model,
            @PathVariable Integer id) throws NotFoundException {

        return ResponseEntity.ok(mapper.entityToDto(service.update(mapper.DtoToEntity(model), id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws NotFoundException {

        service.delete(id);

        return ResponseEntity.ok().build();

    }
}