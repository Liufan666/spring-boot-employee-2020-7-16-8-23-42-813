package com.thoughtworks.springbootemployee.Mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequestDto;
import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;

import com.thoughtworks.springbootemployee.dto.CompanyResponseDto;
import com.thoughtworks.springbootemployee.entity.Company;

import org.springframework.beans.BeanUtils;

public class CompanyMapper {
    public static Company toCompany(CompanyRequestDto companyRequestDto){
        Company company = new Company();
        BeanUtils.copyProperties(companyRequestDto,company);
        return company;
    }
    public static CompanyResponseDto toCompanyResponsDto(Company company){
        CompanyResponseDto companyResponseDto = new CompanyResponseDto();
        BeanUtils.copyProperties(company,companyResponseDto);
        return companyResponseDto;
    }
}
