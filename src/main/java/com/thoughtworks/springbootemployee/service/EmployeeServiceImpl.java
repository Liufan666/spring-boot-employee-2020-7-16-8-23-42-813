package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.utils.PageHelper;
import dto.EmployeeRequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    List<Employee> employees = new ArrayList<>();

    @Override
    public void addEmployee(EmployeeRequestDto employeeRequestDto) {
        Integer company_id = employeeRequestDto.getCompany_id();
        Employee employee = employeeRequestDto.toEntity();
        Company company = companyRepository.findById(company_id).get();
        employee.setCompany(company);
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void deleteEmployee(int id) {

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
    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

}
