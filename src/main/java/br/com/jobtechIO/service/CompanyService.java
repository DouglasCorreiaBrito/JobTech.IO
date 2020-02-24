package br.com.jobtechIO.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jobtechIO.domain.entities.Company;
import br.com.jobtechIO.exceptions.GenericNotFoundException;
import br.com.jobtechIO.repository.CompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    @Autowired
    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> listAllCompanies() {
        return repository.findAll();
    }

    public Company getById(Integer id)   {
        Optional<Company> cOptional = repository.findById(id);
        return cOptional.orElseThrow(() -> new GenericNotFoundException("Company not found"));
    }

    public List<Company> listByName(String name) {
        return repository.findByName(name);
    }

    public Company create(Company entity) {
        entity.setCreatedAt(LocalDate.now());
        return repository.save(entity);
    }

    public void delete(Integer id)   {
        repository.delete(getById(id));
    }

    public Company update(Company entity, Integer id)  {

        Company company = getById(id);

        company.setId(id);
        company.setUpdatedAt(LocalDate.now());
        company.setAddress(entity.getAddress());
        company.setCnpj(entity.getCnpj());
        company.setEmail(entity.getEmail());
        company.setName(entity.getName());
        company.setTelephone(entity.getTelephone());

        return repository.save(company);
    }
}