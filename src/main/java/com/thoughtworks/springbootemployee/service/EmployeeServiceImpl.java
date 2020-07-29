package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

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
        return employeeRepository
                .findAll()
                .stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employees.stream()//todo
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
        employees.remove(employee);
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        for (Employee employeeTarget : employees) {
            if (employeeTarget.getId() == id) {
                employeeTarget.setAge(employee.getAge());
                employeeTarget.setGender(employee.getGender());
                employeeTarget.setName(employee.getName());//todo
            }
        }
    }

    @Override
    public List<Employee> getEmployeeByPage(int page, int pageSize) {
        return PageHelper.page(page, pageSize, employees);
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        return employees.stream()
                .filter(employee -> gender.equals(employee.getGender()))
                .collect(Collectors.toList());//todo
    }
}
