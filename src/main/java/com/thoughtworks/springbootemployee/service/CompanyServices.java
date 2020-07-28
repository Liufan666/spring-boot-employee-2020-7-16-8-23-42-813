package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyServices {
    void addCompanies(Company company);

    Company getCompany(int id);

    List<Company> getCompanies();

    List<Employee> getEmployeesByCompanyId(int id);

    void updateCompany(int id,Company company);

    void deleteCompany(int id);
}
