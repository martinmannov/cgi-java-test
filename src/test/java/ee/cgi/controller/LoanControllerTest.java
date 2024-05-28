package ee.cgi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.cgi.resource.LoanDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
/**
 * Integration testing example, removed from mvn test run
 */
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addLoanEndpoint() throws Exception {
        LoanDto loanDto = new LoanDto();
        loanDto.setPrincipalAmount(new BigDecimal(100000));
        loanDto.setInterestRate(new BigDecimal("3.5"));
        loanDto.setTerm(12);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/loan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(loanDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("principalAmount", is(100000)));
    }

    @Test
    public void fetchAllLoansEndpoint() throws Exception {
        mockMvc.perform(get("/api/loan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].term", is(60)));

    }
}