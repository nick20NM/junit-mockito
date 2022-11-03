package com.alpha.www.JUnitMockito.service;

import com.alpha.www.JUnitMockito.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
