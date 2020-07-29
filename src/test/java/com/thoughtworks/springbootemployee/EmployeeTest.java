package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void should_return_1_employee_when_find_1_employee_by_id_given_1_employee_id_and_1_employee_in_repository() {
        //given
        int employeeId = 1;
        List<Employee> employees = new ArrayList<>();
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        Employee employee = new Employee(1,2,"kevin","male");
        employees.add(employee);

        //when
        Employee employeeGet = employeeService.getEmployeeById(employeeId);

        //then
        assertEquals(employee,employeeGet);
    }
}
