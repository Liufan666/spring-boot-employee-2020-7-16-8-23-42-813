package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void tearDown(){
        employeeRepository.deleteAll();
    }
    @Test
    void should_return_ok_when_get_all_employees_given_0_employees() throws Exception {
        //given
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk());
        //when

        //then
    }

    @Test
    void should_add_one_employee_when_add_new_employee_given_valid_employee() throws Exception {
        String employeeJsonPayload = "{\n" +
                "      \"name\": \"kevin\",\n" +
                "      \"age\": 20,\n" +
                "      \"gender\": \"Male\",\n" +
                "      \"companyId\" : 1\n" +
                "     }";

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJsonPayload)).andExpect(status().isCreated());

        List<Employee> employeeList = employeeRepository.findAll();
//        assertEquals("kevin",employeeList.stream().filter(e -> e.getName() == "kevin").findFirst().get().getName());
        assertEquals(1,employeeList.size());

    }

    @Test
    void should_return_0_employees_when_delete_1_employee_given_1_employees() throws Exception {//todo

        String employeeJsonPayload = "{\n" +
                "      \"name\": \"kevin\",\n" +
                "      \"age\": 20,\n" +
                "      \"gender\": \"Male\",\n" +
                "      \"companyId\" : 1\n" +
                "     }";

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJsonPayload)).andExpect(status().isCreated());
        Integer id = employeeRepository.findAll().get(0).getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/"+id))
                .andExpect(status().is2xxSuccessful());
        List<Employee> employeeList = employeeRepository.findAll();
        assertEquals(0,employeeList.size());
    }

    @Test
    void should_return_keven1113_when_update_employee_given_JSON() throws Exception {
        String employeeJsonPayload = "{\n" +
                "      \"name\": \"keven11111\",\n" +
                "      \"age\": 20,\n" +
                "      \"gender\": \"Male\",\n" +
                "      \"companyId\" : 1\n" +
                "     }";
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJsonPayload)).andExpect(status().isCreated());
        Integer id = employeeRepository.findAll().get(0).getId();
        String employeeJsonPayloadPut = "{\n" +
                "      \"name\": \"keven1113\",\n" +
                "      \"age\": 20,\n" +
                "      \"gender\": \"Male\",\n" +
                "      \"companyId\" : 1\n" +
                "     }";

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/"+id).contentType(MediaType.APPLICATION_JSON).content(employeeJsonPayloadPut)).andExpect(status().is2xxSuccessful());
        assertEquals("keven1113", employeeRepository.findAll().get(0).getName());
    }

    @Test
    void should_return_the_employee_when_find_employee_by_id_given_one_employee() throws Exception {
        String employeeJsonPayload = "{\n" +
                "      \"name\": \"keven11111\",\n" +
                "      \"age\": 20,\n" +
                "      \"gender\": \"Male\",\n" +
                "      \"companyId\" : 1\n" +
                "     }";
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJsonPayload)).andExpect(status().isCreated());
        Integer id = employeeRepository.findAll().get(0).getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/"+id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("name")
                        .value("keven11111"));
    }

}
