package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import dto.EmployeeRequestDto;
import dto.EmployeeResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;

    public EmployeeController(EmployeeService employeeService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequestDto, employee);
        employee.setCompany(companyService.getCompanyById(employeeRequestDto.getCompanyId()));
        employeeService.addEmployee(employee);
    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeesByGender(String gender) {
        return employeeService.getEmployeesByGender(gender);
    }


    @GetMapping
    public List<EmployeeResponseDto> getEmployees(@PageableDefault Pageable pageable, @RequestParam(required = false) boolean unpaged){
        if(unpaged){
            pageable.isUnpaged();
        }
        return employeeService.getEmployees(pageable);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto getEmployeeById(@PathVariable Integer id) {
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        Employee employee = employeeService.getEmployeeById(id);
        BeanUtils.copyProperties(employee, employeeResponseDto);
        employeeResponseDto.setCompanyName(employee.getCompany().getName());
        return employeeResponseDto;
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Integer id, @RequestBody @Valid EmployeeRequestDto employeeRequestDto) {
        employeeService.updateEmployee(id, employeeRequestDto);
    }
}
