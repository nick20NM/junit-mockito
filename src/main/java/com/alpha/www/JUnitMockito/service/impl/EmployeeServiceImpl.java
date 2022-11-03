package com.alpha.www.JUnitMockito.service.impl;

import com.alpha.www.JUnitMockito.entity.Employee;
import com.alpha.www.JUnitMockito.exception.ResourceNotFoundException;
import com.alpha.www.JUnitMockito.repository.EmployeeRepository;
import com.alpha.www.JUnitMockito.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//    @Autowired
    private EmployeeRepository employeeRepository;

    // constructor based dependency injection, @Autowired not required
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedEmployee =employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exists with given email: " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Employee updatedEmployee) {
        return employeeRepository.save(updatedEmployee);
    }
}
