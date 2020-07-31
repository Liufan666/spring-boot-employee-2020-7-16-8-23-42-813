package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import dto.EmployeeRequestDto;
import dto.EmployeeResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public void updateEmployee(Integer id, EmployeeRequestDto employeeRequestDto) {
        Employee employee = getEmployee(employeeRequestDto);//TODO
        employee.setId(id);
        employeeRepository.save(employee);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeResponseDto> getEmployees(Pageable pageable) {
        Page<EmployeeResponseDto> employeeResponseDtoPage;
        List<EmployeeResponseDto> employeeRequestDtoList = new ArrayList<>();
        Page<Employee> employees = employeeRepository.findAll(pageable);
        EmployeeResponseDto employeeResponseDto;
        for (Employee employee : employees) {
            employeeResponseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employee, employeeResponseDto);
            employeeResponseDto.setCompanyName(employee.getCompany().getName());
            employeeRequestDtoList.add(employeeResponseDto);
        }
        return employeeRequestDtoList;
    }

    @Override
    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public Employee getEmployee(EmployeeRequestDto employeeRequestDto) {
        Integer companyId = employeeRequestDto.getCompanyId();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto, employee);
        Company company = companyRepository.findById(companyId).orElse(null);
        employee.setCompany(company);
        return employee;
    }
}
