package com.thoughtworks.springbootemployee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;
    private String name;
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    public Company() {
    }

    public Company(Integer companyId, String name, List<Employee> employees) {
        this.companyId = companyId;
        this.name = name;
        this.employees = employees;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
