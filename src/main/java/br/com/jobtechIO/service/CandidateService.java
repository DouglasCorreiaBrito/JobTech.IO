package br.com.jobtechIO.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jobtechIO.domain.entities.Candidate;
import br.com.jobtechIO.exceptions.GenericNotFoundException;
import br.com.jobtechIO.repository.CandidateRepository;

@Service
public class CandidateService {

	private final CandidateRepository repository;

	@Autowired
	public CandidateService(CandidateRepository repository) {
		this.repository = repository;
	}

	public List<Candidate> listAllCandidates() {
		return repository.findAll();
	}

	public Candidate getById(Integer id) {
		Optional<Candidate> cOptional = repository.findById(id);
		return cOptional.orElseThrow(() -> new GenericNotFoundException("Candidate not found"));
	}

	public List<Candidate> listByName(String name) {
		return repository.findByNameContainingIgnoreCase(name);
	}


	public Candidate create(Candidate entity) {
		entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
		entity.setCreatedAt(LocalDate.now());
		entity.setUpdatedAt(LocalDate.now());
		return repository.save(entity);
	}

	public void delete(Integer id) {
		repository.delete(getById(id));
	}

	public Candidate update(Candidate entity, Integer id) {

		Candidate candidate = getById(id);

		candidate.setId(id);
		candidate.setName(entity.getName());
		candidate.setCPF(entity.getCPF());
		candidate.setEmail(entity.getEmail());
		candidate.setAddress(entity.getAddress());
		candidate.setSkills(entity.getSkills());
		candidate.setEmail(entity.getEmail());
		candidate.setBirthdate(entity.getBirthdate());
		candidate.setTelephone(entity.getTelephone());
		candidate.setUpdatedAt(LocalDate.now());
		candidate.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));

		return repository.save(candidate);
	}
}