package br.com.jobtechIO.steps;

import br.com.jobtechIO.domain.dto.request.JobApplicationRequest;
import br.com.jobtechIO.domain.dto.request.JobOpportunityRequest;
import br.com.jobtechIO.domain.dto.response.JobApplicationResponse;
import br.com.jobtechIO.domain.dto.response.JobOpportunityResponse;
import br.com.jobtechIO.domain.enumerations.*;
import br.com.jobtechIO.utils.IntegrationTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = IntegrationTestConfig.appProperties)
@ActiveProfiles("test")
public class MakeAnApplicationStep {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper mapper;

    @When("the candidate made a post to /job-application")
    public void the_candidate_made_a_post() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/job-application"));
    }

    @Then("the candidate receives status code 201")
    public void candidate_receives_status_code() throws Exception {


        JobApplicationRequest request = JobApplicationRequest.builder().idCandidate(1).idJobOpportunity(1).status(VacantStatus.APPLIED).build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/job-application") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(request))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/job-application/2"))
                .andReturn(); // faz a validação.


        JobApplicationResponse response = mapper.readValue(result.getResponse().getContentAsString(), JobApplicationResponse.class);

        Assert.assertEquals(request.getIdCandidate(), response.candidate.getId());
        Assert.assertEquals(request.getIdJobOpportunity(), response.getJobOpportunity().getId());
        Assert.assertEquals(request.getStatus(), response.getStatus());


    }

}

