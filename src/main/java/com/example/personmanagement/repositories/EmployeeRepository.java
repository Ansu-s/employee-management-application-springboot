package com.example.personmanagement.repositories;

import com.example.personmanagement.entitites.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity,Long> {
}
