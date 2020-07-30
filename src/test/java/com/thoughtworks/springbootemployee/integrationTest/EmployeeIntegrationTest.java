package com.thoughtworks.springbootemployee.integrationTest;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
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

//    @AfterEach
//    void tearDown(){
//        employeeRepository.deleteAll();
//    }
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
                "      \"id\": 5,\n" +
                "      \"name\": \"kevin\",\n" +
                "      \"age\": 20,\n" +
                "      \"gender\": \"Male\",\n" +
                "      \"companyId\" : 1\n" +
                "     }";

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeJsonPayload)).andExpect(status().isCreated());

        List<Employee> employeeList = employeeRepository.findAll();
        assertEquals("kevin",employeeList.stream().filter(e -> e.getId() == 5).findFirst().get().getName());
    }

    @Test
    void should_return_4_employees_when_delete_1_employee_given_5_employees() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1"))
                .andExpect(status().is2xxSuccessful());
        List<Employee> employeeList = employeeRepository.findAll();
        assertEquals(4,employeeList.size());
    }

    @Test
    void should_return_keven1_when_updage_id_2_employee_given_JSON() throws Exception {
        String employeeJsonPayload = "{\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"keven1\",\n" +
                "      \"age\": 20,\n" +
                "      \"gender\": \"Male\",\n" +
                "      \"companyId\" : 1\n" +
                "     }";

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/2").contentType(MediaType.APPLICATION_JSON).content(employeeJsonPayload)).andExpect(status().is2xxSuccessful());
        assertEquals("keven1", employeeRepository.findAll().stream().filter(e -> e.getId() == 2).findFirst().get().getName());
    }

    @Test
    void should_return_id_3_employee_when_find_employee_by_id_given_employee_id_3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("name")
                        .value("haifeng"));
    }
}
