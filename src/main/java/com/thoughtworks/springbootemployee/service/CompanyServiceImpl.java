package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import dto.CompanyRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void addCompanies(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Page<Company> getCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }


    @Override
    public List<Employee> getEmployeesByCompanyId(Integer id) {
        return companyRepository.findById(id).get().getEmployees();//TODO
    }

    @Override
    public void updateCompany(Integer id, Company company) {
        company.setCompanyId(id);
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }

}
