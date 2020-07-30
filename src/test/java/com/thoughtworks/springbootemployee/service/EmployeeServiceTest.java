package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyServiceImpl;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import com.thoughtworks.springbootemployee.service.EmployeeServiceImpl;
import dto.EmployeeRequestDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willCallRealMethod;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
//    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private static EmployeeRequestDto employeeRequestDto;

    @BeforeAll
    static void createEmployee(){
        employeeRequestDto = new EmployeeRequestDto();
        employeeRequestDto.setId(1);
        employeeRequestDto.setAge(1);
        employeeRequestDto.setCompanyId(1);
        employeeRequestDto.setGender("male");
        employeeRequestDto.setName("vicky");
    }

    @Test
    void should_return_all_employees_when_find_all_given_none() {
        //given

        //when
        employeeService.getEmployees(Pageable.unpaged());

        //then
        verify(employeeService).getEmployees(Pageable.unpaged());
    }

    @Test
    void should_return_special_gender_employee_when_find_employee_by_gender_given_gender() {
        //given
        String gender = "female";
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee(1,2,"vicky","female");
        employees.add(employee);
        when(employeeRepository.findByGender(gender)).thenReturn(employees);

        //when
        List<Employee> employeesByGender = employeeService.getEmployeesByGender(gender);

        //then
        assertEquals(employees,employeesByGender);
    }
}
