package com.thoughtworks.springbootemployee.Mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.dto.EmployeeResponseDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeMapper {
    public static Employee toEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto,employee);
        return employee;
    }
    public static EmployeeResponseDto toEmployeeResponseDto(Employee employee){
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(employee,employeeResponseDto);
        return employeeResponseDto;
    }
}
