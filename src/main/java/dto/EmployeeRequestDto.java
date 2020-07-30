package dto;

import com.thoughtworks.springbootemployee.entity.Employee;

public class EmployeeRequestDto {

    private Integer id;
    private Integer age;
    private String name;
    private String gender;
    private Integer companyId;

    public Employee toEntity() {
        return new Employee(id, age, name, gender);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public EmployeeRequestDto(Integer id, Integer age, String name, String gender, Integer companyId) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.companyId = companyId;
    }

    public EmployeeRequestDto() {
    }
}
