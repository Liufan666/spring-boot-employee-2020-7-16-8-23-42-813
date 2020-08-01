package com.thoughtworks.springbootemployee.dto;

public class CompanyResponseDto {
    private String name;

    public CompanyResponseDto(String name) {
        this.name = name;
    }

    public CompanyResponseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
