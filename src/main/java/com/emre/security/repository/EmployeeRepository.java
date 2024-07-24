package com.emre.security.repository;

import com.emre.security.model.Department;
import com.emre.security.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByFirstName(String firstName);

    public Employee findByLastName(String lastName);

    public Employee findByEmailId(String emailId);


}
