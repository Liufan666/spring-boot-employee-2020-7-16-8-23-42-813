package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.utils.PageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private List<Company> companies = new ArrayList<>();

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void addCompanies(Company company) {
        companyRepository.save(company);
    }//todo

    @Override
    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public List<Employee> getEmployeesByCompanyId(Integer id) {
        return companyRepository.findById(id).get().getEmployees();
    }

    @Override
    public void updateCompany(int id,Company company) {
        for (Company companyTarget : companies) {
            if (companyTarget.getCompanyId() == id) {
                companyTarget.setEmployees(company.getEmployees());
            }
        }
    }

    @Override
    public void deleteCompany(int id) {
        Company company = companies.stream()//todo
                .filter(e -> e.getCompanyId() == id)
                .findFirst()
                .orElse(null);
        companies.remove(company);
    }

    @Override
    public List<Company> getCompanyByPage(int page, int pageSize) {
        return PageHelper.page(page,pageSize,companies);

    }
}
