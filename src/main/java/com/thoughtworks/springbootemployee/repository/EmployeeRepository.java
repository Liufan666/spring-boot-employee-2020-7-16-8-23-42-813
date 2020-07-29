package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository {
    List<Employee> findAll();
}
