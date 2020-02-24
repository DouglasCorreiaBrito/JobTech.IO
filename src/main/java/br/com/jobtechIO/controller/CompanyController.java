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

import br.com.jobtechIO.domain.dto.request.CompanyRequest;
import br.com.jobtechIO.domain.dto.response.CompanyResponse;
import br.com.jobtechIO.domain.entities.Company;
import br.com.jobtechIO.domain.mapper.CompanyMapper;
import br.com.jobtechIO.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService service;
    private final CompanyMapper mapper;

    public CompanyController(CompanyService service, CompanyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(mapper.entityToDto(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAll() {
        return ResponseEntity
                .ok(service.listAllCompanies().stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
    }

    @GetMapping(value = "filtered/{name}")
    public ResponseEntity<List<CompanyResponse>> filterByName(@Valid @PathVariable String name) {
        return ResponseEntity
                .ok(service.listByName(name).stream().map(x -> mapper.entityToDto(x)).collect(Collectors.toList()));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompanyResponse> post(@Valid @RequestBody CompanyRequest model,
            UriComponentsBuilder uriComponentsBuilder) {

        Company entity = service.create(mapper.DtoToEntity(model));

        URI uri = uriComponentsBuilder.path("/company/{id}").buildAndExpand(entity.getId()).toUri();

        return ResponseEntity.created(uri).body(mapper.entityToDto(entity));

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyResponse> put(@Valid @RequestBody CompanyRequest model, @PathVariable Integer id) {

        return ResponseEntity.ok(mapper.entityToDto(service.update(mapper.DtoToEntity(model), id)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.ok().build();

    }
}