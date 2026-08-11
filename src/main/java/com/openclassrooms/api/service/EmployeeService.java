package com.openclassrooms.api.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.openclassrooms.api.model.Employee;
import com.openclassrooms.api.repository.EmployeeRepository;

import lombok.Data;

@Data 
@Service //annotation Spring pour indiquer que la classe est un bean, et que son rôle est de fournir des services

public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id); 
    }

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(final Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
