package br.com.jobtechIO.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jobtechIO.domain.entities.Skill;
import br.com.jobtechIO.exceptions.GenericNotFoundException;
import br.com.jobtechIO.repository.SkillRepository;

@Service
public class SkillService {

    private final SkillRepository repository;

    @Autowired
    public SkillService(SkillRepository repository) {
        this.repository = repository;
    }

    public List<Skill> listAllSkills() {
        return repository.findAll();
    }

    public Skill getById(Integer id)   {
        Optional<Skill> cOptional = repository.findById(id);
        return cOptional.orElseThrow(() -> new GenericNotFoundException("Skill not found"));
    }

    public List<Skill> listByDescription(String name) {
        return repository.findByDescription(name);
    }

    public Skill create(Skill entity) {
        entity.setCreatedAt(LocalDate.now());
        return repository.save(entity);
    }

    public void delete(Integer id)   {
        repository.delete(getById(id));
    }

    public Skill update(Skill entity, Integer id)  {

        Skill Skill = getById(id);

        Skill.setId(id);
        Skill.setDescription(entity.getDescription());
        Skill.setUpdatedAt(LocalDate.now());

        return repository.save(Skill);
    }
}