package br.com.jobtechIO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jobtechIO.domain.entities.Deficiency;

@Repository
public interface DeficiencyRepository extends JpaRepository<Deficiency, Integer> {

	List<Deficiency> findByDescription(String description);
}