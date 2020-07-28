package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public String addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return "add success";
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(@RequestParam(defaultValue =  "-1",required = false) int page,@RequestParam(defaultValue = "-1",required = false) int pageSize,@RequestParam(defaultValue = "gender",required = false) String gender){
        if (page != -1 && pageSize != -1){
            return employeeService.getEmployeeByPage(page,pageSize);
        } else if (gender != null){
            return employeeService.getEmployeeByGender(gender);
        }
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployee(@PathVariable int id,@RequestBody Employee employee){
        employeeService.updateEmployee(id,employee);
    }
}
