package br.com.jobtechIO.steps;

import br.com.jobtechIO.domain.dto.request.JobApplicationRequest;
import br.com.jobtechIO.domain.dto.request.JobOpportunityRequest;
import br.com.jobtechIO.domain.dto.response.JobApplicationResponse;
import br.com.jobtechIO.domain.dto.response.JobOpportunityResponse;
import br.com.jobtechIO.domain.entities.JobApplication;
import br.com.jobtechIO.domain.entities.JobOpportunity;
import br.com.jobtechIO.domain.enumerations.*;
import br.com.jobtechIO.service.JobApplicationService;
import br.com.jobtechIO.utils.IntegrationTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matchers;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = IntegrationTestConfig.appProperties)
@ActiveProfiles("test")
public class ManegeApplicationsStep {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JobApplicationService service;

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

    @When("the candidate made a get to /job-application")
    public ResultActions candidate_made_a_get() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/job-application"));

    }

    @Then("the candidate receives status code 200")
    public  ResultActions candidate_receives_status_code_200() throws Exception {

        return candidate_made_a_get().andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @And("the candidate receives a list of job applications")
    public void the_candidate_receives_a_list_of_job_applications() throws Exception {

        candidate_receives_status_code_200().andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @When("the candidate made a get to /job-application with id")
    public ResultActions candidate_made_a_get_by_id() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/job-application/1"));

    }

    @Then("the candidate receives status code 200 by get by id")
    public ResultActions candidate_receives_status_code_200_by_get_by_id() throws Exception {

        return candidate_made_a_get_by_id().andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @And("the candidate receives a job application")
    public void the_candidate_receives_job_opportunity() throws Exception {

        JobApplication job = service.getById(1);

        MvcResult result= candidate_receives_status_code_200_by_get_by_id().andReturn();

        JobApplicationResponse response = mapper.readValue(result.getResponse().getContentAsString(), JobApplicationResponse.class);
        Assert.assertEquals(job.getId(), response.getId());
    }

    @When("the candidate made a put to /job-application")
    public void the_candidate_made_a_put() throws Exception {

    }

    @Then("the candidate receives status code 200 with put")
    public void candidate_receives_status_code_200_put() throws Exception {

        JobApplicationRequest request = JobApplicationRequest.builder().idCandidate(1).idJobOpportunity(1).status(VacantStatus.CANCELED).build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/job-application/1") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(request))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn(); // faz a validação.


        JobApplicationResponse response = mapper.readValue(result.getResponse().getContentAsString(), JobApplicationResponse.class);

        Assert.assertEquals(request.getIdCandidate(), response.candidate.getId());
        Assert.assertEquals(request.getIdJobOpportunity(), response.getJobOpportunity().getId());
        Assert.assertEquals(request.getStatus(), response.getStatus());


    }

    @When("the candidate made a delete to /job-application")
    public void candidate_made_a_del() throws Exception {

        System.out.println("this step is skipped cause the next step realize delete");

    }

    @Then("the candidate receives status code 200 with delete")
    public void candidate_receives_status_code_200_with_delete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/job-application/2")).andExpect(MockMvcResultMatchers.status().isOk());
    }


}

