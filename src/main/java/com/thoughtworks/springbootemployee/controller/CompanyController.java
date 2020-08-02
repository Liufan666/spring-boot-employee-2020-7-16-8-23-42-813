package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController//todo
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public void addCompanies(@RequestBody @Valid CompanyRequestDto companyRequestDto) {
        Company company = CompanyMapper.toCompany(companyRequestDto);
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
    public void updateCompany(@PathVariable Integer id, @RequestBody @Valid CompanyRequestDto companyRequestDto) {
        Company company = CompanyMapper.toCompany(companyRequestDto);
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
