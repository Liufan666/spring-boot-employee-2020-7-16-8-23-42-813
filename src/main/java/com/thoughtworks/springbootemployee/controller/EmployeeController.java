package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public String addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return "add success";
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeesByGender(String gender){
        return employeeService.getEmployeesByGender(gender);
    }


//    @GetMapping("/employees")
//    public List<Employee> getEmployees(@RequestParam(required = false,value = "page") Integer page,
//                                       @RequestParam(value = "pageSize",required = false) Integer pageSize,
//                                       @RequestParam(value = "gender",required = false) String gender){
//        if (page != null && pageSize != null){
//            return employeeService.getEmployeeByPage(page,pageSize);
//        } else if (gender != null){//todo
//            return employeeService.getEmployeeByGender(gender);
//        }
//        return employeeService.getEmployees();
//    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable int id,@RequestBody Employee employee){
        employeeService.updateEmployee(id,employee);
    }
}
