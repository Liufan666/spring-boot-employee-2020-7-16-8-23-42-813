package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    List<Employee> employees = new ArrayList<>();

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;

    }

    @Override
    public Employee getEmployee(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
