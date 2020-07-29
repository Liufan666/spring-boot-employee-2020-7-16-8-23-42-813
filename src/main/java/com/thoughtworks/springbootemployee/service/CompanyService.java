package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    void addCompanies(Company company);

    Company getCompanyById(Integer id);

    List<Company> getCompanies();

    List<Employee> getEmployeesByCompanyId(Integer id);

    void updateCompany(Integer id,Company company);

    void deleteCompany(Integer id);

    List<Company> getCompanyByPage(int page, int pageSize);
}
