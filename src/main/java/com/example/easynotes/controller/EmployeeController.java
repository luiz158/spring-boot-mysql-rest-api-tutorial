package com.example.easynotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.easynotes.exception.ResourceNotFoundException;
import com.example.easynotes.model.Employee;
import com.example.easynotes.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
    EmployeeRepository employeeRepository;
    
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/employees")
    public Employee createNote(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                                           @Valid @RequestBody Employee employeeDetails) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employee.setName(employeeDetails.getName());
        employee.setGender(employeeDetails.getGender());
        employee.setAge(employeeDetails.getAge());
        employee.setAddress(employeeDetails.getAddress());

        Employee updatedEmployee = employeeRepository.save(employee);
        return updatedEmployee;
    }
    
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
    }

}
