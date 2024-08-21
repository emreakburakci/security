package com.emre.security.controller;

import com.emre.security.model.Employee;
import com.emre.security.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/createEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee);
    }

    //update employee
    @PutMapping("/updateEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    //delete employee
    @DeleteMapping("/deleteEmployee/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    //get employee by id
    @GetMapping("/getEmployee/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    //get employee by first name
    @GetMapping("/getEmployeeByFirstName/{firstName}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Employee getEmployeeByFirstName(@PathVariable String firstName) {
        return employeeService.getEmployeeByFirstName(firstName);
    }

    //get employee by last name
    @GetMapping("/getEmployeeByLastName/{lastName}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Employee getEmployeeByLastName(@PathVariable String lastName) {
        return employeeService.getEmployeeByLastName(lastName);
    }


    //get all employees
    @GetMapping("/getAllEmployees")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeService.getAllEmployees(pageable);
    }
}
