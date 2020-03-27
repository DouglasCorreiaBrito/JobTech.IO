package br.com.jobtechIO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jobtechIO.domain.entities.JobOpportunity;

@Repository
public interface JobOpportunityRepository extends JpaRepository<JobOpportunity, Integer> {

	List<JobOpportunity> findByLocationContainingIgnoreCase(String location);

	List<JobOpportunity> findByTitleContainingIgnoreCase(String title);

	@Query(nativeQuery = true, value =  "SELECT * FROM JobOpportunity WHERE typeOfContract like %:contract%")
	List<JobOpportunity> findByTypeOfContract(String contract);

	@Query(nativeQuery = true, value =  "SELECT * FROM JobOpportunity WHERE location like %:location% and title like %:title% and typeOfContract like %:contract%")
	List<JobOpportunity> findWithComboFilter(String location, String title, String contract);

	long count();
}