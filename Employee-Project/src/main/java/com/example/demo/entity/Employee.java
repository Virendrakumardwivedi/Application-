package com.example.demo.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String empName;
	    private String department;
	    private double amount;
	    private String currency;

	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM-dd-yyyy")
	    private LocalDate joiningDate;

	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM-dd-yyyy")
	    private LocalDate exitDate;
	    
	    public Employee(String empName, String department, int amount, String currency, LocalDate joiningDate, LocalDate exitDate) {
	        this.empName = empName;
	        this.department = department;
	        this.amount = amount;
	        this.currency = currency;
	        this.joiningDate = joiningDate;
	        this.exitDate = exitDate;
	    }


}
