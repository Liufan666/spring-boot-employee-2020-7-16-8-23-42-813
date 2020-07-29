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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyServiceImpl companyService;

    @Test
    void should_return_all_employees_when_find_all_given_none() {
        //given
        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl(employeeRepository, companyRepository);

        //when
        employeeServiceImpl.getEmployees(Pageable.unpaged());

        //then
        verify(employeeRepository).findAll(Pageable.unpaged());
    }

    @Test
    void should_return_1_employee_when_find_employee_in_company_given_() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl(employeeRepository, companyRepository);

        //when
        List<Employee> employeeList2 = companyService.getEmployeesByCompanyId(1);

        //then
        assertEquals(5,employeeList2);
    }
}
