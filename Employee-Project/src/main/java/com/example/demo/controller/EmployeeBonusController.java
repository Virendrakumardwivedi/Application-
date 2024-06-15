package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tci")
@Slf4j
public class EmployeeBonusController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employee-bonus")
    public ResponseEntity<String> saveEmployeeBonus(@RequestBody Map<String, List<Employee>> request) {
    
    	log.info("inside employee post mapping");
        List<Employee> employees = request.get("employees");
        employeeRepository.saveAll(employees);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employees Created successfully.");
        
    }

    @GetMapping("/employee-bonus")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getEmployeeBonus(
            @RequestParam @DateTimeFormat(pattern = "MMM-dd-yyyy") LocalDate date) {
    	
    	log.info("inside employee get mapping");
        List<Employee> employees = employeeRepository.findAll();
        
        Map<String, List<Map<String, Object>>> response = employees.stream()
                .filter(employee -> !employee.getJoiningDate().isAfter(date) && !employee.getExitDate().isBefore(date))
                .collect(Collectors
                		.groupingBy(Employee::getCurrency,
                        Collectors.
                        mapping(emp -> Map.of("empName", emp.getEmpName(), "amount", emp.getAmount()), Collectors.toList())));
        return ResponseEntity.ok(response);
        
    }    

}
