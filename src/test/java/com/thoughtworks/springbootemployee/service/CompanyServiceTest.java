package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyServiceImpl companyService;

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
