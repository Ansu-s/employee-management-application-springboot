package com.example.employee.services.implementations;

import com.example.employee.entitites.EmployeeEntity;
import com.example.employee.exceptions.AppRuntimeException;
import com.example.employee.models.Employee;
import com.example.employee.repositories.EmployeeRepository;
import com.example.employee.services.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.save(employee.toEntity());
        return new Employee().fromEntity(employeeEntity);
    }

    @Override
    public List<Employee> getAllEmployees() {
        Iterable<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employeeList = new ArrayList<>();
        for (EmployeeEntity entity : employeeEntities) {
            Employee empl = new Employee().fromEntity(entity);
            employeeList.add(empl);
        }
        return employeeList;
    }

    public Employee getEmployeeById(Long id) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);
        if (optionalEmployeeEntity.isPresent()) {
            EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
            return new Employee().fromEntity(employeeEntity);
        } else {
            throw new AppRuntimeException("No employee found with ID: " + id);
        }
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> criteriaQuery = criteriaBuilder.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> root = criteriaQuery.from(EmployeeEntity.class);

        Predicate predicate = criteriaBuilder.equal(root.get("name"), name);
        criteriaQuery.where(predicate);

        List<EmployeeEntity> employeeEntities = entityManager.createQuery(criteriaQuery).getResultList();
        List<Employee> employeeList = employeeEntities.stream().map(e -> new Employee().fromEntity(e)).toList();
        return employeeList;
    }
   
    // used JPQL perform the search operation (Is this the JPA Query mentioned in the assignment)
    public Employee getEmployeeByAge(Long age) {
        try {
            Query query = entityManager.createQuery("SELECT emp FROM EmployeeEntity emp WHERE emp.age = :age");
            query.setParameter("age", age);
            EmployeeEntity employeeEntity = (EmployeeEntity) query.getSingleResult();
            return new Employee().fromEntity(employeeEntity);
        } catch (Exception e) {
            throw new AppRuntimeException("No employee found with age: " + age);
        }
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity existingEmployeeEntity = employeeRepository.findById(id).orElseThrow(() -> new AppRuntimeException("Employee not found with id: " + id));

        existingEmployeeEntity.setName(employee.getName());
        existingEmployeeEntity.setAge(employee.getAge());
        existingEmployeeEntity.setSalary(employee.getSalary());
        existingEmployeeEntity.setAddress(employee.getAddress());

        EmployeeEntity updatedEmployeeEntity = employeeRepository.save(existingEmployeeEntity);
        return new Employee().fromEntity(updatedEmployeeEntity);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new AppRuntimeException("Employee doesn't exist with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
