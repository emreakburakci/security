package com.emre.security.controller;

import com.emre.security.model.Employee;
import com.emre.security.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
//TEST
//@CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private PagedResourcesAssembler<Employee> pagedResourcesAssembler;

    @PostMapping("/createEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeRepository.save(employee);
    }

    //update employee
    @PutMapping("/updateEmployee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //delete employee
    @DeleteMapping("/deleteEmployee/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }

    //get employee by id
    @GetMapping("/getEmployee/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeRepository.findById(id).get();
    }

    //get employee by first name
    @GetMapping("/getEmployeeByFirstName/{firstName}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Employee getEmployeeByFirstName(@PathVariable String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    //get employee by last name
    @GetMapping("/getEmployeeByLastName/{lastName}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Employee getEmployeeByLastName(@PathVariable String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    //get employee by email id
    @GetMapping("/getEmployeeByEmailId/{emailId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public Employee getEmployeeByEmailId(@PathVariable String emailId) {
        return employeeRepository.findByEmailId(emailId);
    }




    //get all employees
    @GetMapping("/getAllEmployees")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public PagedModel<EntityModel<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(employeePage);
    }
}
