package br.com.jobtechIO.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jobtechIO.domain.entities.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	List<Candidate> findByNameContainingIgnoreCase(String name);
	Optional<Candidate> findByEmail(String email);
}