package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.entity.Employee;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CompanyRequestDto {

    @NotBlank
    private String name;
    private List<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public CompanyRequestDto(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public CompanyRequestDto() {
    }
}
