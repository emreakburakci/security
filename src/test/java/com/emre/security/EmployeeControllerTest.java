package com.emre.security;

import com.emre.security.controller.EmployeeController;
import com.emre.security.model.Department;
import com.emre.security.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeController employeeController;

    private List<Employee> employeeList;

    @BeforeEach
    public void setUp() {

        Department d = new Department(1L,"IT", "Information Technology","it@it.com"     );
        this.employeeList = Arrays.asList(
            new Employee(1L,"John", "Doe", "john.doe@example.com",d),
            new Employee(2L,"Jane", "Doe", "jane.doe@example.com",d)
        );
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void testGetAllEmployees() throws Exception {
        Page<Employee> employeePage = new PageImpl<>(employeeList, PageRequest.of(0, 5), employeeList.size());
        when(employeeController.getAllEmployees(PageRequest.of(0, 5))).thenReturn(employeePage);

        mockMvc.perform(get("/employee/getAllEmployees")
                .param("page", "0")
                .param("size", "5")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());



    }

    // Add more test methods as needed
}