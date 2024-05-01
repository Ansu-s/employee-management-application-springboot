package com.example.employee.models;

import com.example.employee.entitites.EmployeeEntity;
import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String name;
    private int age;
    private double salary;
    private String address;

    public EmployeeEntity toEntity() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(this.getId());
        employeeEntity.setName(this.getName());
        employeeEntity.setAge(this.getAge());
        employeeEntity.setSalary(this.getSalary());
        employeeEntity.setAddress(this.getAddress());
        return employeeEntity;
    }

    public Employee fromEntity(EmployeeEntity entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setAge(entity.getAge());
        this.setSalary(entity.getSalary());
        this.setAddress(entity.getAddress());
        return this;
    }
}
