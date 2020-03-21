package br.com.jobtechIO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jobtechIO.domain.entities.JobApplication;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

	List<JobApplication> findByJobOpportunity_id(Integer id);

	List<JobApplication> findByCandidate_id(Integer id);

	List<JobApplication> findByCandidate_name(String name);

	List<JobApplication> findByCandidate_CPF(String cpf);

}