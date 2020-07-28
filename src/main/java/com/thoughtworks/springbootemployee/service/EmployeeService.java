package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void addEmployee(Employee employee);

    List<Employee> getEmployees();

    Employee getEmployee(int id);

    void deleteEmployee(int id);

    void updateEmployee(int id, Employee employee);

    List<Employee> getEmployeeByPage(int page, int pageSize);

    List<Employee> getEmployeeByGender(String gender);
}
