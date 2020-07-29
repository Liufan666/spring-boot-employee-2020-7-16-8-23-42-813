package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import dto.EmployeeRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public void addEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = getEmployee(employeeRequestDto);
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(NotFoundException:: new);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public void updateEmployee(Integer id,EmployeeRequestDto employeeRequestDto) {
        Employee employee = getEmployee(employeeRequestDto);
        employee.setId(id);
        employeeRepository.save(employee);
    }

    @Override
    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    private Employee getEmployee(EmployeeRequestDto employeeRequestDto) {
        Integer companyId = employeeRequestDto.getCompanyId();
        Employee employee = employeeRequestDto.toEntity();
        Company company = companyRepository.findById(companyId).get();
        employee.setCompany(company);
        return employee;
    }
}
