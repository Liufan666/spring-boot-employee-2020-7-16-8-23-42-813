package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import dto.EmployeeRequestDto;
import dto.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public interface EmployeeService {
    EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto);

    Employee getEmployeeById(Integer id);

    void deleteEmployee(Integer id);

    void updateEmployee(Integer id,EmployeeRequestDto employeeRequestDto);

    Page<Employee> getEmployees(Pageable pageable);

    List<Employee> getEmployeesByGender(String gender);
}
