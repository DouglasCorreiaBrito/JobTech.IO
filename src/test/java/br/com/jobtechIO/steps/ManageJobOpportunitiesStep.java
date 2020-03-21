package br.com.jobtechIO.steps;

import br.com.jobtechIO.domain.dto.request.JobOpportunityRequest;
import br.com.jobtechIO.domain.dto.response.JobOpportunityResponse;
import br.com.jobtechIO.domain.entities.JobOpportunity;
import br.com.jobtechIO.domain.enumerations.ContractEnum;
import br.com.jobtechIO.domain.enumerations.ExperienceEnum;
import br.com.jobtechIO.domain.enumerations.JobOpportunityStatusEnum;
import br.com.jobtechIO.domain.enumerations.YesNoPartial;
import br.com.jobtechIO.service.JobOpportunityService;
import br.com.jobtechIO.utils.IntegrationTestConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ManageJobOpportunitiesStep {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JobOpportunityService service;

    @When("the company made a post to /jobs")
    public void the_company_made_a_post() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/jobs"));
    }

    @Then("the company receives status code 201")
    public void company_receives_status_code() throws Exception {


        JobOpportunityRequest request = JobOpportunityRequest.builder()
                .benefits("string to match")
                .companyId(1)
                .deficiencies(new ArrayList<Integer>())
                .description("string to match")
                .location("string to match")
                .minimumWage(1.0)
                .office("string to match")
                .remote(YesNoPartial.YES)
                .seniority(ExperienceEnum.ENGINEER)
                .skills(new ArrayList<Integer>())
                .status(JobOpportunityStatusEnum.OPEN)
                .title("string to match")
                .typeOfContract(ContractEnum.CLT).build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/jobs") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(request))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/jobs/2"))
                .andReturn(); // faz a validação.


        JobOpportunityResponse response = mapper.readValue(result.getResponse().getContentAsString(), JobOpportunityResponse.class);

        Assert.assertEquals(request.getCompanyId(), response.getCompany().getId());
        Assert.assertEquals(request.getDeficiencies(), response.getDeficiencies());
        Assert.assertEquals(request.getSkills(), response.getSkills());
        Assert.assertEquals(request.getDescription(), response.getDescription());

    }

    @When("the company made a get to /jobs")
    public ResultActions company_made_a_get() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/jobs"));

    }

    @Then("the company receives status code 200")
    public  ResultActions company_receives_status_code_200() throws Exception {

        return company_made_a_get().andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @And("the company receives a list of job opportunities")
    public void the_company_receives_a_list_of_job_opportunities() throws Exception {

        company_receives_status_code_200().andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @When("the company made a get to /jobs with id")
    public ResultActions company_made_a_get_by_id() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/jobs/1"));

    }

    @Then("the company receives status code 200 by get by id")
    public ResultActions company_receives_status_code_200_by_get_by_id() throws Exception {

        return company_made_a_get_by_id().andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @And("the company receives a job opportunity")
    public void the_company_receives_job_opportunity() throws Exception {

        JobOpportunity job = service.getById(1);

        MvcResult result= company_receives_status_code_200_by_get_by_id().andReturn();

        JobOpportunityResponse response = mapper.readValue(result.getResponse().getContentAsString(), JobOpportunityResponse.class);
        Assert.assertEquals(job.getCompany().getId(), response.getCompany().getId());
    }

    @When("the company made a get to /jobs filtered by title")
    public ResultActions company_made_a_get_by_title() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/jobs/filtered/string"));
    }

    @Then("the company receives status code 200 by get by title")
    public ResultActions company_receives_status_code_200_by_get_by_title() throws Exception {

       return company_made_a_get_by_title().andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @And("the company receives a job opportunity with that title")
    public void company_receives_a_job_opportunity_with_that_title() throws Exception {

      company_receives_status_code_200_by_get_by_title().andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));;

    }

    @When("the company made a put to /jobs")
    public void the_company_made_a_put() throws Exception {

    }

    @Then("the company receives status code 200 with put")
    public void company_receives_status_code_200_with_put() throws Exception {


        JobOpportunityRequest request = JobOpportunityRequest.builder()
                .benefits("string to match")
                .companyId(1)
                .deficiencies(new ArrayList<Integer>())
                .description("string to match")
                .location("string to match")
                .minimumWage(1.0)
                .office("string to match")
                .remote(YesNoPartial.YES)
                .seniority(ExperienceEnum.ENGINEER)
                .skills(new ArrayList<Integer>())
                .status(JobOpportunityStatusEnum.OPEN)
                .title("string to match")
                .typeOfContract(ContractEnum.CLT).build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/jobs/1") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(request))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn(); // faz a validação.


        JobOpportunityResponse response = mapper.readValue(result.getResponse().getContentAsString(), JobOpportunityResponse.class);

        Assert.assertEquals(request.getCompanyId(), response.getCompany().getId());
        Assert.assertEquals(request.getDeficiencies(), response.getDeficiencies());
        Assert.assertEquals(request.getSkills(), response.getSkills());
        Assert.assertEquals(request.getDescription(), response.getDescription());

    }

    @When("the company made a delete to /jobs")
    public void company_made_a_del() throws Exception {

        System.out.println("this step is skipped cause the next step realize delete");

    }

    @Then("the company receives status code 200 with delete")
    public void company_receives_status_code_200_with_delete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/jobs/2")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}

