package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    void addCompanies(Company company);

    Company getCompanyById(Integer id);

    Page<Company> getCompanies(Pageable pageable);

    List<Employee> getEmployeesByCompanyId(Integer id);

    void updateCompany(Integer id,Company company);

    void deleteCompany(Integer id);


}
