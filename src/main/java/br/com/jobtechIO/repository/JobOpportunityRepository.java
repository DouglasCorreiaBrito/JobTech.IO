package br.com.jobtechIO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jobtechIO.domain.entities.JobOpportunity;

@Repository
public interface JobOpportunityRepository extends JpaRepository<JobOpportunity, Integer> {

	List<JobOpportunity> findByTitleContainingIgnoreCase(String name);
}