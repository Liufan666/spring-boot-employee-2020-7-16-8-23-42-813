package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyServiceImpl {
    void addCompanies(Company company);

    Company getCompany(int id);

    List<Company> getCompanies();
}
