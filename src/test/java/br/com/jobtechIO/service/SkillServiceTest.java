package br.com.jobtechIO.service;

import br.com.jobtechIO.domain.entities.Candidate;
import br.com.jobtechIO.domain.entities.JobOpportunity;
import br.com.jobtechIO.domain.entities.Skill;
import br.com.jobtechIO.repository.SkillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {

    @InjectMocks
    private SkillService service;

    @Mock
    private SkillRepository repository;

    @Autowired
    public Skill entity;

    public SkillServiceTest() {
        this.entity = Skill.builder().description("string to match")
                .candidates(new ArrayList<Candidate>())
                .jobOpportunities(new ArrayList<JobOpportunity>())
                .build();

    }

    @Test
    public void should_listSkills() {

        List<Skill> list = new ArrayList<>();
        when(repository.findAll()).thenReturn(list);
        List<Skill> model = service.listAllSkills();
        assertEquals(list,model);
    }
}
