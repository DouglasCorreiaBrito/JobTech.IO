package br.com.jobtechIO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jobtechIO.domain.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

    List<Company> findByName(String name);
}