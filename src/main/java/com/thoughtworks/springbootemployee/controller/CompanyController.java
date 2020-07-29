package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyServiceImpl;
import com.thoughtworks.springbootemployee.service.CompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//todo
public class CompanyController {

    @Autowired
    private CompanyServices companyService;//todo

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
    public List<Company> getCompanies(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyService.getCompanyByPage(page, pageSize);
        }
        return companyService.getCompanies();
    }
}
