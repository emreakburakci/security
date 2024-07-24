package com.emre.security;

import com.emre.security.model.Employee;
import com.emre.security.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/employee/createEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"emailId\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/employee/getAllEmployees"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/employee/getEmployee/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(put("/employee/updateEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"emailId\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmailId("john.doe@example.com");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(delete("/employee/deleteEmployee/1"))
                .andExpect(status().isOk());
    }
}
