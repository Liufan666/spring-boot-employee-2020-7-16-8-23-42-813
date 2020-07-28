package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.stereotype.Service;

@Service
public interface CompanyServiceImpl {
    void addCompanies(Company company);

    Company getCompany(int id);
}
