package br.com.jobtechIO.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jobtechIO.domain.entities.Deficiency;
import br.com.jobtechIO.repository.DeficiencyRepository;
import javassist.NotFoundException;

@Service
public class DeficiencyService {

    private final DeficiencyRepository repository;

    @Autowired
    public DeficiencyService(DeficiencyRepository repository) {
        this.repository = repository;
    }

    public List<Deficiency> listAllDeficiencies() {
        return repository.findAll();
    }

    public Deficiency getById(Integer id) throws NotFoundException {
        Optional<Deficiency> dOptional = repository.findById(id);
        return dOptional.orElseThrow(() -> new NotFoundException("Deficiency not found"));
    }

    public List<Deficiency> listByDescription(String description) {
        return repository.findByDescription(description);
    }

    public Deficiency create(Deficiency entity) {
        entity.setCreatedAt(LocalDate.now());
        return repository.save(entity);
    }

    public void delete(Integer id) throws NotFoundException {
        repository.delete(getById(id));
    }

    public Deficiency update(Deficiency entity, Integer id) throws NotFoundException {

        Deficiency deficiency = getById(id);

        deficiency.setDescription(entity.getDescription());
        deficiency.setId(id);
        deficiency.setUpdatedAt(LocalDate.now());

        return repository.save(entity);
    }
}