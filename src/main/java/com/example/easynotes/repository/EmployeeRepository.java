package com.example.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.easynotes.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
