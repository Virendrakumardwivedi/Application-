package com.example.demo.test;


import com.example.demo.controller.EmployeeBonusController;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeBonusController.class)
@AutoConfigureMockMvc
public class EmployeeBonusControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeBonusController employeeBonusController;

//    @Test
//    public void saveEmployeeBonus_ValidInput_ShouldReturnCreated() throws Exception {
//        Employee employee = new Employee("John Doe", "IT", 1000, "USD", LocalDate.of(2024, 6, 15), null);
//        List<Employee> employees = Collections.singletonList(employee);
//
//        when(employeeRepository.saveAll(any())).thenReturn(employees);
//
//        mockMvc.perform(post("/tci/employee-bonus")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(Map.of("employees", employees))))
//                .andExpect(status().isCreated())
//                .andExpect(content().string("Employees Created successfully."));
//    }
//
//    @Test
//    public void getEmployeeBonus_ValidDate_ShouldReturnFilteredEmployees() throws Exception {
//        Employee employee1 = new Employee("John Doe", "IT", 1000, "USD", LocalDate.of(2024, 6, 1), null);
//        Employee employee2 = new Employee("Jane Smith", "HR", 1500, "EUR", LocalDate.of(2024, 6, 15), null);
//        List<Employee> employees = Arrays.asList(employee1, employee2);
//
//        when(employeeRepository.findAll()).thenReturn(employees);
//
//        mockMvc.perform(get("/tci/employee-bonus")
//                .param("date", "Jun-15-2024"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.USD[0].empName").value("John Doe"))
//                .andExpect(jsonPath("$.USD[0].amount").value(1000.0));
//    }
//	
    
    @Test
    public void saveEmployeeBonus_ValidInput_ShouldReturnCreated() throws Exception {
        Employee employee = new Employee("John Doe", "IT", 1000, "USD", LocalDate.of(2024, 6, 15), null);
        List<Employee> employees = Collections.singletonList(employee);

        when(employeeRepository.saveAll(any())).thenReturn(employees);

        mockMvc.perform(post("/tci/employee-bonus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("employees", employees))))
                .andExpect(status().isCreated())
                .andExpect(content().string("Employees Created successfully."));
    }

    @Test
    public void getEmployeeBonus_ValidDate_ShouldReturnFilteredEmployees() throws Exception {
        Employee employee1 = new Employee("John Doe", "IT", 1000, "USD", LocalDate.of(2024, 6, 1), null);
        Employee employee2 = new Employee("Jane Smith", "HR", 1500, "EUR", LocalDate.of(2024, 6, 15), null);
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        mockMvc.perform(get("/tci/employee-bonus")
                .param("date", "2024-06-15"))  // Ensure date format matches your controller's expectations
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.USD[0].empName").value("John Doe"))
                .andExpect(jsonPath("$.USD[0].amount").value(1000));
    }
}
