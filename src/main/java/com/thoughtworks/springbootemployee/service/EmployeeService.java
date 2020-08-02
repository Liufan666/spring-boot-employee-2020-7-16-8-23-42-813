package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public interface EmployeeService {
    void addEmployee(Employee employee);

    Employee getEmployeeById(Integer id);

    void deleteEmployee(Integer id);

    void updateEmployee(Integer id,Employee employee);

    List<EmployeeResponseDto> getEmployees(Pageable pageable);

    List<EmployeeResponseDto> getEmployeesByGender(String gender);
}
