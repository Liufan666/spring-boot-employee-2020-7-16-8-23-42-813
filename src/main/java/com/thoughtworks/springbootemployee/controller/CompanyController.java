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
    public void addCompanies(@RequestBody Company company) {
        companyService.addCompanies(company);
    }

    @GetMapping("/companies/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompany(id);
    }


    @GetMapping("/companies/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) {
        return companyService.getEmployeesByCompanyId(id);
    }

    @PutMapping("/companies/{id}")
    public void updateCompany(@PathVariable int id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
    }

    @DeleteMapping("/companies/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }



    @GetMapping("/companies")
    public List<Company> getCompanies(@RequestParam(defaultValue = "-1", required = false) int page, @RequestParam(defaultValue = "-1", required = false) int pageSize) {
        if (page != -1 && pageSize != -1) {
            return companyService.getCompanyByPage(page, pageSize);
        }
        return companyService.getCompanies();
    }
}
