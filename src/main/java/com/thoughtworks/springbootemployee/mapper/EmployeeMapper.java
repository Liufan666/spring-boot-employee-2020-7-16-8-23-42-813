package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import dto.EmployeeRequestDto;
import dto.EmployeeResponseDto;

public class EmployeeMapper {

    public static Employee getEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee.setId(employeeRequestDto.getId());
        employee.setName(employeeRequestDto.getName());
        employee.setGender(employeeRequestDto.getGender());
        employee.setAge(employeeRequestDto.getAge());
        return employee;
    }

    public static EmployeeResponseDto getEmployeeResponseDto(Employee employee){
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        employeeResponseDto.setAge(employee.getAge());
        employeeResponseDto.setName(employee.getName());
        employeeResponseDto.setGender(employee.getGender());
        employeeResponseDto.setCompanyName(employee.getCompany().getName());
        return employeeResponseDto;
    }
}
