package com.example.employee.entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class EmployeeEntity {
    @Id
    private Long id;
    private String name;
    private int age;
    private double salary;
    private String address;

}
