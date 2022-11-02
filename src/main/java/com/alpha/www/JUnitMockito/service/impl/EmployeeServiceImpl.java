package com.alpha.www.JUnitMockito.service.impl;

import com.alpha.www.JUnitMockito.entity.Employee;
import com.alpha.www.JUnitMockito.exception.ResourceNotFoundException;
import com.alpha.www.JUnitMockito.repository.EmployeeRepository;
import com.alpha.www.JUnitMockito.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedEmployee =employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exists with given email: " + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }
}
