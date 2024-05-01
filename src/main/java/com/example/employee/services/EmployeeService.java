package com.example.employee.services;

import com.example.employee.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    List<Employee> findEmployeeByName(String name);

    Employee getEmployeeByAge(Long age);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);
}
