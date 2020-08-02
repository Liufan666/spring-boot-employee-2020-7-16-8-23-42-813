package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    void tearDown(){
        companyRepository.deleteAll();
    }

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
                "      \"employees\": [{\n" +
                "            \"id\": 1,\n" +
                "            \"age\": 52,\n" +
                "            \"name\": \"chengcheng\",\n" +
                "            \"gender\": \"male\"\n" +
                "        }]\n" +
                "}";

        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(companyJsonPayload)).andExpect(status().is2xxSuccessful());

        List<Company> companies = companyRepository.findAll();
        assertEquals(1,companies.size());
    }

    @Test
    void should_return_0_company_when_delete_1_company_given_1_company() throws Exception {

        String companyJsonPayload = "{\n" +
                "      \"name\": \"ooct\",\n" +
                "      \"employees\": [{\n" +
                "            \"id\": 1,\n" +
                "            \"age\": 52,\n" +
                "            \"name\": \"chengcheng\",\n" +
                "            \"gender\": \"male\"\n" +
                "        }]\n" +
                "}";

        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(companyJsonPayload)).andExpect(status().is2xxSuccessful());
        Integer companyId = companyRepository.findAll().get(0).getCompanyId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/"+companyId))
                .andExpect(status().is2xxSuccessful());
        List<Company> companyList = companyRepository.findAll();
        assertEquals(0,companyList.size());
    }

    @Test
    void should_return_oocl_when_update_id_2_company_given_JSON() throws Exception {
        String companyJsonPayload = "{\n" +
                "      \"name\": \"oocl\",\n" +
                "      \"company_id\":2,\n" +
                "      \"employees\": [{\n" +
                "            \"id\": 1,\n" +
                "            \"age\": 52,\n" +
                "            \"name\": \"chengcheng\",\n" +
                "            \"gender\": \"male\"\n" +
                "        }]\n" +
                "}";


        mockMvc.perform(MockMvcRequestBuilders.put("/companies/2").contentType(MediaType.APPLICATION_JSON).content(companyJsonPayload)).andExpect(status().is2xxSuccessful());
        assertEquals("oocl", companyRepository.findAll().stream().filter(e -> e.getCompanyId() == 2).findFirst().get().getName());
    }
}