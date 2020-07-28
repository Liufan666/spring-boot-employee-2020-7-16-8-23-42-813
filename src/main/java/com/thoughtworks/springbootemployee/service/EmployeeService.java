package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    void addEmployee(Employee employee);
}
