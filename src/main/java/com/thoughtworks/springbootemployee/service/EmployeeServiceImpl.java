package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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

    @Override
    public void deleteEmployee(int id) {
        Employee employee = employees.stream()
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
                employeeTarget.setName(employee.getName());
            }
        }
    }

    @Override
    public List<Employee> getEmployeeByPage(int page, int pageSize) {
        List<Employee> currentEmployees = new ArrayList<>();
        int startIndex = (page-1)*pageSize;
        int endIndex = pageSize*page;
        for (int index = startIndex; index < Math.min(endIndex, employees.size()); index++) {
                currentEmployees.add(employees.get(index));
        }
        return currentEmployees;
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        return employees.stream()
                .filter(employee -> gender.equals(employee.getGender()))
                .collect(Collectors.toList());
    }
}
