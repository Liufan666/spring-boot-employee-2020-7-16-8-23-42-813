package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import dto.EmployeeRequestDto;
import dto.EmployeeResponseDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
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

    @Test
    void should_return_1_employee_when_add_1_employee_given_1_employee() {
        //given
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(1,12,"kevin","male",1);

//        Employee employee = EmployeeMapper.getEmployee(employeeRequestDto);
        Company company = new Company(1,"oocl",new ArrayList<>());

//        when(employeeRepository.save(any())).thenReturn(employee);
        when(companyRepository.findById(company.getCompanyId())).thenReturn(Optional.of(company));

        //when
//        Employee employeeSaved = employeeService.addEmployee(employeeRequestDto);
        EmployeeResponseDto result = employeeService.addEmployee(employeeRequestDto);
        //then
        assertEquals(employeeRequestDto.getName(),result.getName());
    }

    @Test
    void should_return_employee_when_mapper_given_employeeDto() {
        //given
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(1,17,"kevin","male",1);


        //when
        Employee employee = EmployeeMapper.getEmployee(employeeRequestDto);

        //then
        assertEquals(employee.getName(),employeeRequestDto.getName());
    }
}
