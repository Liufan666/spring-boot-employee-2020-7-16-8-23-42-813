package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private CompanyRepository  companyRepository;


    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_ok_when_get_all_companies_given_0() throws Exception {
        //given
        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(status().isOk());
        //when

        //then
    }

    @Test
    void should_add_one_company_when_add_new_company_given_valid_company() throws Exception {
        String companyJsonPayload = "{\n" +
                "      \"name\": \"ooct\",\n" +
                "      \"company_id\":2,\n" +
                "      \"employees\": [{\n" +
                "            \"id\": 1,\n" +
                "            \"age\": 52,\n" +
                "            \"name\": \"chengcheng\",\n" +
                "            \"gender\": \"male\"\n" +
                "        }]\n" +
                "}";

        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(companyJsonPayload)).andExpect(status().is2xxSuccessful());

        List<Company> companies = companyRepository.findAll();
        assertEquals("ooct",companies.stream().filter(e -> e.getCompanyId() == 2).findFirst().get().getName());
    }
}