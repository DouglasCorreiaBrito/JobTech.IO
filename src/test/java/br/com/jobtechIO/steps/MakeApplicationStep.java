package br.com.jobtechIO.steps;

import br.com.jobtechIO.domain.dto.request.JobOpportunityRequest;
import br.com.jobtechIO.domain.dto.response.JobOpportunityResponse;
import br.com.jobtechIO.domain.entities.Company;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MakeApplicationStep {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper mapper;

    @When("the client made a post to /jobOpportunity")
    public void the_client_made_a_post() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/job-opportunity"));
    }

    @Then("the client receives status code of created")
    public void client_receives_status_code() throws Exception {


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

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/job-opportunity") //
                .contentType(MediaType.APPLICATION_JSON) //
                .content(mapper.writeValueAsString(request))) // Executa
                .andDo(MockMvcResultHandlers.print()) // pega resultado
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/jobs/1"))
                .andReturn(); // faz a validação.


        JobOpportunityResponse response = mapper.readValue(result.getResponse().getContentAsString(), JobOpportunityResponse.class);

        Assert.assertEquals(request.getCompanyId(), response.getCompany().getId());
        Assert.assertEquals(request.getDeficiencies(), response.getDeficiencies());
        Assert.assertEquals(request.getSkills(), response.getSkills());
        Assert.assertEquals(request.getDescription(), response.getDescription());

    }

}

