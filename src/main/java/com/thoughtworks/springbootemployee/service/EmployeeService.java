package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import dto.EmployeeRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void addEmployee(EmployeeRequestDto employeeRequestDto);

    List<Employee> getEmployees();

    Employee getEmployeeById(Integer id);

    void deleteEmployee(Integer id);

    void updateEmployee(Integer id,EmployeeRequestDto employeeRequestDto);

    List<Employee> getEmployeeByPage(int page, int pageSize);

    List<Employee> getEmployeesByGender(String gender);
}
