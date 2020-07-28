package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @PostMapping("/companies")
    public void addCompanies(@RequestBody Company company){
        companyService.addCompanies(company);
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable int id){
        return companyService.getCompany(id);
    }

    @GetMapping("/companies")
    public List<Company> getCompanies(){
        return companyService.getCompanies();
    }

    @GetMapping("/companies/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id){
        return companyService.getEmployeesByCompanyId(id);
    }
}
