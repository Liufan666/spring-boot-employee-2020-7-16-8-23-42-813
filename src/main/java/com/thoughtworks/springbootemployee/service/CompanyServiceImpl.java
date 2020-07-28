package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyServices {

    private List<Company> companies = new ArrayList<>();

    public void addCompanies(Company company) {
        companies.add(company);
    }

    @Override
    public Company getCompany(int id) {
        return companies.stream()
                .filter(company -> company.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public List<Employee> getEmployeesByCompanyId(int id) {
        return companies.stream()
                .filter(company -> company.getId() == id)
                .findFirst()
                .orElse(null).getEmployees();
    }

    @Override
    public void updateCompany(int id,Company company) {
        for (Company companyTarget : companies) {
            if (companyTarget.getId() == id) {
                companyTarget.setEmployees(company.getEmployees());
            }
        }
    }

    @Override
    public void deleteCompany(int id) {
        Company company = companies.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
        companies.remove(company);
    }
}
