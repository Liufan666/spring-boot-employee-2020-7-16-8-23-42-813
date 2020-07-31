package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//todo
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public void addCompanies(@RequestBody Company company) {
        companyService.addCompanies(company);
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Integer id) {
        return companyService.getCompanyById(id);
    }


    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable Integer id) {
        return companyService.getEmployeesByCompanyId(id);
    }

    @PutMapping("/{id}")
    public void updateCompany(@PathVariable Integer id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
    }

    @GetMapping
    public Page<Company> getCompanies(@PageableDefault Pageable pageable, @RequestParam(required = false) boolean unpaged){//
        if(unpaged){
            return companyService.getCompanies(Pageable.unpaged());
        }
        return companyService.getCompanies(pageable);
    }
}
