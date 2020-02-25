package br.com.jobtechIO.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jobtechIO.domain.entities.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>{

    List<Skill> findByDescription(String name);
}